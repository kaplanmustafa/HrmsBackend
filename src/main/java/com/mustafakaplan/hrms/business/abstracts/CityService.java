package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.entities.concretes.City;

import java.util.List;

public interface CityService {

    DataResult<City> findById(int id);

    DataResult<List<City>> getAll();
}
