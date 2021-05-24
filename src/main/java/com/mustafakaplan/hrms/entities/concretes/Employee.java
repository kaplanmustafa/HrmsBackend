package com.mustafakaplan.hrms.entities.concretes;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private String identityNumber;

    private int birthYear;

    private String email;

    private String password;

    private boolean isVerified;
}
