package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Company;

import java.util.List;

public interface CompanyService {

    Result add(Company company);

    DataResult<List<Company>> getAll();
}
