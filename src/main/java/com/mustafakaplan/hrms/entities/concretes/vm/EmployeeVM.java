package com.mustafakaplan.hrms.entities.concretes.vm;

import lombok.Data;

@Data
public class EmployeeVM {

    private String name;

    private String surname;

    private String identityNumber;

    private int birthYear;

    private String email;

    private String password;

    private String passwordRepeat;
}
