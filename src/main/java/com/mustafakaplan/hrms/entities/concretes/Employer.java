package com.mustafakaplan.hrms.entities.concretes;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Employer {

    @Id
    @GeneratedValue
    private int id;

    private String companyName;

    private String website;

    private String email;

    private String phone;

    private String password;

    private boolean isVerifiedEmail;

    private boolean isVerifiedAccount;
}
