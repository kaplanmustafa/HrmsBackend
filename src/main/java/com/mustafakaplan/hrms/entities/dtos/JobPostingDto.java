package com.mustafakaplan.hrms.entities.dtos;

import com.mustafakaplan.hrms.core.utilities.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobPostingDto {

    private String companyName;

    private String positionName;

    private int numberOfEmployees;

    private String startDate;

    private String endDate;

    public JobPostingDto(String companyName, String positionName, int numberOfEmployees, Date startDate, Date endDate) {
        this.companyName = companyName;
        this.positionName = positionName;
        this.numberOfEmployees = numberOfEmployees;
        this.startDate = DateUtil.convertDateToTrStringFormatFromTimestamp(startDate);
        this.endDate = DateUtil.convertDateToTrStringFormatFromTimestamp(endDate);
    }
}
