package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Technology;

public interface TechnologyService {

    Result add(Technology technology);
}
