package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Company;

import java.util.List;

public interface CompanyService {

    Result add(Company company);

    DataResult<List<Company>> getAll();
}
