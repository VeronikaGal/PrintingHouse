package com.galeeva.project.servlet;

import com.galeeva.project.dto.CreateOrderDto;
import com.galeeva.project.dto.UserDto;
import com.galeeva.project.entity.OrderDelivery;
import com.galeeva.project.entity.OrderPaperType;
import com.galeeva.project.exeption.ValidationException;
import com.galeeva.project.service.OrderDataService;
import com.galeeva.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.galeeva.project.util.UrlPath.ACCEPT_ORDER;
import static com.galeeva.project.util.UrlPath.CREATE_ORDER;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(CREATE_ORDER)
public class CreateOrderServlet extends HttpServlet {

    public final OrderDataService orderDataService = OrderDataService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("paperTypes", OrderPaperType.values());
        req.setAttribute("deliveries", OrderDelivery.values());

        Integer serviceId = Integer.valueOf(req.getParameter("serviceId"));
        req.setAttribute("serviceId", serviceId);

        req.getRequestDispatcher(JspHelper.getPath("createOrder"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var user = (UserDto) req.getSession().getAttribute("user");
        Integer serviceId = Integer.valueOf(req.getParameter("serviceId"));
        var orderDto = CreateOrderDto.builder()
                .userId(user.getId())
                .serviceId(serviceId)
                .file(req.getPart("file"))
                .paperType(req.getParameter("paperType"))
                .quantity(Integer.valueOf(req.getParameter("quantity")))
                .delivery(req.getParameter("delivery"))
                .build();
        try {
            orderDataService.create(orderDto);
            resp.sendRedirect(ACCEPT_ORDER);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
