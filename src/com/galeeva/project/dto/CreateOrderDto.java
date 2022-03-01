package com.galeeva.project.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOrderDto {

    Long userId;
    Integer serviceId;
    Part file;
    String paperType;
    Integer quantity;
    String status;
    String delivery;
}
