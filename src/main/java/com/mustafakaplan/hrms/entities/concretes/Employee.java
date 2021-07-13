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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties
    private Users user;

    private String identityNumber;

    private int birthYear;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Cv cv;
}
