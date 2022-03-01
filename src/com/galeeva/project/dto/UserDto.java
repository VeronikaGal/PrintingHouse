package com.galeeva.project.dto;

import com.galeeva.project.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Long id;
    String name;
    String phoneNumber;
    String address;
    Role role;
    String email;
    String password;
}
