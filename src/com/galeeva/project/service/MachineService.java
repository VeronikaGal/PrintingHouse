package com.galeeva.project.service;

import com.galeeva.project.dao.MachineDao;
import com.galeeva.project.dto.MachineDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MachineService {

    private static final MachineService INSTANCE = new MachineService();

    private final MachineDao machineDao = MachineDao.getInstance();

    public List<MachineDto> findAll() {
        return machineDao.findAll().stream()
                .map(machine -> MachineDto.builder()
                        .id(machine.getId())
                        .description("""
                                  %s - %s 
                                """.formatted(machine.getModel(), machine.getType()))
                        .build())
                .collect(toList());
    }

    public static MachineService getInstance() {
        return INSTANCE;
    }
}
