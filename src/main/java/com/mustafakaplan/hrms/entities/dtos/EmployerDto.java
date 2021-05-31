package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployerDto {

    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String website;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String passwordRepeat;
}
