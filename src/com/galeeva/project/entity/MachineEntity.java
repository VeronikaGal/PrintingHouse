package com.galeeva.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MachineEntity {

    private Integer id;
    private MachineModel model;
    private MachineType type;
}
