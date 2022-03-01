package com.galeeva.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDataEntity {

    private Long id;
    private Long userId;
    private Integer serviceId;
    private String file;
    private OrderPaperType paperType;
    private Integer quantity;
    private Integer machineId;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private OrderDelivery delivery;
}
