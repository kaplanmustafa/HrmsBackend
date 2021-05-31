package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import com.mustafakaplan.hrms.entities.dtos.JobPostingSubmitDto;
import com.mustafakaplan.hrms.entities.dtos.JobPostingDto;

import java.util.List;

public interface JobPostingService {

    DataResult<List<JobPosting>> getAll();

    Result add(JobPostingSubmitDto jobPosting);

    DataResult<List<JobPostingDto>> getAllActivePosting();

    DataResult<List<JobPostingDto>> getAllActivePostingByDateDesc();

    DataResult<List<JobPostingDto>> getAllActivePostingByDateAsc();

    DataResult<List<JobPostingDto>> getAllActivePostingByCompany(String companyWebsite);
}
