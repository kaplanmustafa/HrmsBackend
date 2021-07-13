package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.School;

public interface SchoolService {

    Result add(School school);
}
