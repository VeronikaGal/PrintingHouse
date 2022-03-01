package com.galeeva.project.service;

import com.galeeva.project.dao.ServiceDao;
import com.galeeva.project.dto.ServiceDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ServiceService {

    private static final ServiceService INSTANCE = new ServiceService();

    private final ServiceDao serviceDao = ServiceDao.getInstance();

    public Optional<ServiceDto> findServiceById(Integer id) {
        return serviceDao.findById(id)
                .map(serviceDto -> ServiceDto.builder()
                        .id(serviceDto.getId())
                        .name(serviceDto.getName())
                        .description(serviceDto.getDescription())
                        .price(serviceDto.getPrice())
                        .build());
    }

    public List<ServiceDto> findAll() {
        return serviceDao.findAll().stream()
                .map(service -> ServiceDto.builder()
                        .id(service.getId())
                        .image(service.getImage())
                        .description(
                                """
                                        %s - %s - %f
                                        """.formatted(service.getName(), service.getDescription(),
                                        service.getPrice()))
                        .build())
                .collect(toList());
    }

    public static ServiceService getInstance() {
        return INSTANCE;
    }
}

