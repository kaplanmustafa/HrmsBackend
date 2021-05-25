package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.vm.EmployerVM;

public interface EmployerService {

    Result add(EmployerVM employer);
}
