package com.galeeva.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ReportOrderDto {

    Long id;
    String description;
}
