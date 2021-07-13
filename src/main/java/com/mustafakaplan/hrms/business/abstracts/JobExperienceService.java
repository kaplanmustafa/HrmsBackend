package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobExperience;

public interface JobExperienceService {

    Result add(JobExperience jobExperience);
}
