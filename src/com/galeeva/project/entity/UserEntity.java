package com.galeeva.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private Role role;
    private String email;
    private String password;
}

