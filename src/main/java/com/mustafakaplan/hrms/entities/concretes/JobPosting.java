package com.mustafakaplan.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1000)
    private String description;

    private double minSalary;

    private double maxSalary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private int numberOfEmployees;

    @ManyToOne
    private JobPosition jobPosition;

    @ManyToOne
    private Company company;

    @ManyToOne
    private City city;

    @ManyToOne
    private JobType jobType;

    private boolean isRemote;

    private boolean isActive;

    private boolean isApproved;
}
