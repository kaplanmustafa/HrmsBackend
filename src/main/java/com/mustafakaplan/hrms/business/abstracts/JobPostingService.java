package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import com.mustafakaplan.hrms.entities.concretes.vm.JobPostingSubmitVM;
import com.mustafakaplan.hrms.entities.concretes.vm.JobPostingVM;

import java.util.List;

public interface JobPostingService {

    DataResult<List<JobPosting>> getAll();

    Result add(JobPostingSubmitVM jobPosting);

    DataResult<List<JobPostingVM>> getAllActivePosting();
}
