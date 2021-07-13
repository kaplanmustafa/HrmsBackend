package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;

    private UserDto user;

    private String userType;
}
