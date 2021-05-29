package com.mustafakaplan.hrms.entities.concretes.vm;

import lombok.Data;

import java.util.Date;

@Data
public class JobPostingSubmitVM {

    private String description;

    private double minSalary;

    private double maxSalary;

    private Date endDate;

    private int numberOfEmployees;

    private int jobPositionId;

    private String employerEmail;

    private int cityId;
}
