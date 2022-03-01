package com.galeeva.project.mapper;

import com.galeeva.project.dto.CreateOrderDto;
import com.galeeva.project.entity.OrderDataEntity;
import com.galeeva.project.entity.OrderDelivery;
import com.galeeva.project.entity.OrderPaperType;
import com.galeeva.project.entity.OrderStatus;

import java.time.LocalDateTime;

public class CreateOrderMapper implements Mapper<CreateOrderDto, OrderDataEntity> {

    public static final String FILE_FOLDER = "db.file.folder";
    public static final CreateOrderMapper INSTANCE = new CreateOrderMapper();

    @Override
    public OrderDataEntity mapFrom(CreateOrderDto object) {
        return OrderDataEntity.builder()
                .userId(object.getUserId())
                .serviceId(object.getServiceId())
                .file(FILE_FOLDER + object.getFile().getSubmittedFileName())
                .paperType(OrderPaperType.valueOf(object.getPaperType()))
                .quantity(object.getQuantity())
                .status(OrderStatus.NEW)
                .createdAt(LocalDateTime.now())
                .deliveredAt(LocalDateTime.now().plusHours(1L))
                .delivery(OrderDelivery.valueOf(object.getDelivery()))
                .build();
    }

    public static CreateOrderMapper getInstance() {
        return INSTANCE;
    }
}
