package com.galeeva.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ServiceEntity {

    private Integer id;
    private String image;
    private ServiceName name;
    private String description;
    private BigDecimal price;
}
