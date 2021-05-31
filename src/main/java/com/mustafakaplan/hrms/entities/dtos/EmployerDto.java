package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

@Data
public class EmployerDto {

    private String companyName;

    private String website;

    private String email;

    private String phone;

    private String password;

    private String passwordRepeat;
}
