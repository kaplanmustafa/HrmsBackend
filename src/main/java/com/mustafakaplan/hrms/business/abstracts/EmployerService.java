package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.dtos.EmployerDto;

import java.util.List;

public interface EmployerService {

    Result add(EmployerDto employer);

    DataResult<List<Employer>> getAll();
}
