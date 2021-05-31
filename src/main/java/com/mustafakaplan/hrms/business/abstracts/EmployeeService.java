package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Result add(EmployeeDto employee);

    DataResult<List<Employee>> getAll();
}
