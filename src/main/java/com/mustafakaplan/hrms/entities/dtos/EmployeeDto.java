package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

@Data
public class EmployeeDto {

    private String name;

    private String surname;

    private String identityNumber;

    private int birthYear;

    private String email;

    private String password;

    private String passwordRepeat;
}
