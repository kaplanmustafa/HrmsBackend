package com.mustafakaplan.hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

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

    private boolean isVerifiedEmail;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Cv cv;
}
