package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {

    Result add(JobPosition jobPosition);

    DataResult<List<JobPosition>> getAll();

    DataResult<JobPosition> getById(int id);
}
