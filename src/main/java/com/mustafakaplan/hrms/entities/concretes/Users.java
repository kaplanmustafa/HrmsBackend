package com.mustafakaplan.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean isVerifiedEmail;

    private String userType;
}
