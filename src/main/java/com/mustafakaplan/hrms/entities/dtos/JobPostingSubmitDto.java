package com.mustafakaplan.hrms.entities.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class JobPostingSubmitDto {

    @NotNull
    @NotBlank
    private String description;

    private double minSalary;

    private double maxSalary;

    private Date endDate;

    @NotNull
    private int numberOfEmployees;

    @NotNull
    private int jobPositionId;

    @Email
    private String employerEmail;

    @NotNull
    private int cityId;

    @NotNull
    private int jobTypeId;

    @NotNull
    private boolean isRemote;
}
