package com.galeeva.project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MachineDto {

    private Integer id;
    private String description;
}
