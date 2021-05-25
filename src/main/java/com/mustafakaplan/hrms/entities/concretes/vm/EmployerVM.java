package com.mustafakaplan.hrms.entities.concretes.vm;

import lombok.Data;

@Data
public class EmployerVM {

    private String companyName;

    private String website;

    private String email;

    private String phone;

    private String password;

    private String passwordRepeat;
}
