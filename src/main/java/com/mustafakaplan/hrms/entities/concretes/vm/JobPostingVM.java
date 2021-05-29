package com.mustafakaplan.hrms.entities.concretes.vm;

import com.mustafakaplan.hrms.core.utilities.DateUtil;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import lombok.Data;

@Data
public class JobPostingVM {

    private String companyName;

    private String positionName;

    private int numberOfEmployees;

    private String startDate;

    private String endDate;

    public JobPostingVM(JobPosting jobPosting) {
        this.companyName = jobPosting.getEmployer().getCompanyName();
        this.positionName = jobPosting.getJobPosition().getJobTitle();
        this.numberOfEmployees = jobPosting.getNumberOfEmployees();
        this.startDate = DateUtil.convertDateToTrStringFormatFromTimestamp(jobPosting.getStartDate());
        this.endDate = DateUtil.convertDateToTrStringFormatFromTimestamp(jobPosting.getEndDate());
    }
}
