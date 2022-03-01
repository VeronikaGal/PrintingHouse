package com.galeeva.project.service;

import com.galeeva.project.util.PropertiesUtil;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FileService {

    private static final FileService INSTANCE = new FileService();

    private final String basePath = PropertiesUtil.get("file.base.url");

    @SneakyThrows
    public void upload(String filePath, InputStream fileContent) {
        Path reportFullPath = Path.of(basePath, filePath);
        try (fileContent) {
            Files.createDirectories(reportFullPath.getParent());
            Files.write(reportFullPath, fileContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    public static FileService getInstance() {
        return INSTANCE;
    }
}
