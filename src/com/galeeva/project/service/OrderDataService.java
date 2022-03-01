package com.galeeva.project.service;

import com.galeeva.project.dao.OrderDataDao;
import com.galeeva.project.dto.CreateOrderDto;
import com.galeeva.project.dto.OrderDataDto;
import com.galeeva.project.exeption.ValidationException;
import com.galeeva.project.mapper.CreateOrderMapper;
import com.galeeva.project.validator.CreateOrderValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderDataService {

    private static final OrderDataService INSTANCE = new OrderDataService();

    private final CreateOrderValidator createOrderValidator = CreateOrderValidator.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
    private final OrderDataDao orderDataDao = OrderDataDao.getInstance();
    private final FileService fileService = FileService.getInstance();

    public List<OrderDataDto> findAllByServiceid(Long serviceId) {
        return orderDataDao.findAllByServiceId(serviceId).stream()
                .map(orderData -> new OrderDataDto(
                        orderData.getId()))
                .collect(toList());
    }

    @SneakyThrows
    public Long create(CreateOrderDto orderDto) {
        var validationResult = createOrderValidator.isValid(orderDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var orderEntity = createOrderMapper.mapFrom(orderDto);
        fileService.upload(orderEntity.getFile(), orderDto.getFile().getInputStream());
        orderDataDao.save(orderEntity);
        return orderEntity.getId();
    }

    public static OrderDataService getInstance() {
        return INSTANCE;
    }
}
