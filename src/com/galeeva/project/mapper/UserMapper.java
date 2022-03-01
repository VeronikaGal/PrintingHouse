package com.galeeva.project.mapper;

import com.galeeva.project.dto.UserDto;
import com.galeeva.project.entity.UserEntity;

public class UserMapper implements Mapper<UserEntity, UserDto> {

    public static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .phoneNumber(object.getPhoneNumber())
                .address(object.getAddress())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
