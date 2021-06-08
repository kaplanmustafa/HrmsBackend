package com.mustafakaplan.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cv"})
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
