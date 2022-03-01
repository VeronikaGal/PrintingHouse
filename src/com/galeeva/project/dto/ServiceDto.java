package com.galeeva.project.dto;

import com.galeeva.project.entity.ServiceName;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ServiceDto {

    Integer id;
    String image;
    ServiceName name;
    String description;
    BigDecimal price;
}
