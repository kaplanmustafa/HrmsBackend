package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.concretes.vm.EmployerVM;

import java.util.List;

public interface EmployerService {

    Result add(EmployerVM employer);

    DataResult<List<Employer>> getAll();

    DataResult<Employer> findByEmail(String email);
}
