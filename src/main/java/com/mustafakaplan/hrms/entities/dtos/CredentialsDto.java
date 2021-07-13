package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

@Data
public class CredentialsDto {

    private String email;

    private String password;

    private String userType;
}
