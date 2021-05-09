package com.mustafakaplan.hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Job {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Column(length = 1000)
    private String description;

    private double salary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
