package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobType;

import java.util.List;

public interface JobTypeService {

    DataResult<JobType> findById(int id);

    DataResult<List<JobType>> getAll();

    Result add(JobType jobType);
}
