package com.galeeva.project.util;

import com.galeeva.project.exeption.DaoException;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String DRIVER_KEY = "db.driver";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final Integer DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        var poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnections = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var proxyConnection = (Connection)
                    Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                            (proxy, method, args) -> method.getName().equals("close")
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException exception) {
            throw new DaoException(exception);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtil.get(DRIVER_KEY));
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public static void closePool() {
        try {
            for (Connection sourceConnection : sourceConnections) {
                sourceConnection.close();
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }
}

