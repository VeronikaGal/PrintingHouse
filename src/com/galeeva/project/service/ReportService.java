package com.galeeva.project.service;

import com.galeeva.project.dao.OrderDataDao;
import com.galeeva.project.dto.ReportOrderDto;
import com.galeeva.project.util.PropertiesUtil;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ReportService {

    public static final ReportService INSTANCE = new ReportService();
    private final OrderDataDao orderDataDao = OrderDataDao.getInstance();

    private final String basePath = PropertiesUtil.get("report.base.url");
    private final String filePath = "printing_house.csv";

    public List<ReportOrderDto> findAll() throws IOException {
        return orderDataDao.findAll().stream()
                .map(orderData -> ReportOrderDto.builder()
                        .id(orderData.getId())
                        .description("""
                                %d, %d, %s, %s, %d, %d, %s, %f, %s, %s, %s
                                """.formatted(orderData.getUserId(), orderData.getServiceId(), orderData.getFile(),
                                orderData.getPaperType(), orderData.getQuantity(), orderData.getMachineId(), orderData.getStatus(),
                                orderData.getTotalPrice(), orderData.getCreatedAt(), orderData.getDeliveredAt(), orderData.getDelivery()))
                        .build()).toList();
    }

    public static ReportService getInstance() {
        return INSTANCE;
    }
}



