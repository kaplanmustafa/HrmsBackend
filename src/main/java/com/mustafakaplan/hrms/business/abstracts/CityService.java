package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.entities.concretes.City;

public interface CityService {

    DataResult<City> findById(int id);
}
