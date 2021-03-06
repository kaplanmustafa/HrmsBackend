package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Result add(EmployeeDto employee);

    DataResult<List<Employee>> getAll();

    DataResult<Employee> getEmployeeByIdentityNumber(String identityNumber);
}
