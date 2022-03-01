package com.galeeva.project.servlet;

import com.galeeva.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.galeeva.project.util.UrlPath.ACCEPT_ORDER;

@WebServlet(ACCEPT_ORDER)
public class AcceptOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(JspHelper.getPath("acceptOrder"))
                .forward(req, resp);
    }
}
