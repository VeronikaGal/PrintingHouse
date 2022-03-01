package com.galeeva.project.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.galeeva.project.util.UrlPath.REPORT;

@WebServlet(REPORT)
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Disposition", "attachment; fileName=\"printing_house.csv\"");
        resp.setContentType("text/csv");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var outputStream = resp.getOutputStream();
             var stream = ReportServlet.class.getClassLoader().getResourceAsStream("printing_house.csv")) {
            assert stream != null;
            outputStream.write(stream.readAllBytes());
        }
    }
}
