package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.CityService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.ErrorDataResult;
import com.mustafakaplan.hrms.core.results.SuccessDataResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.CityDao;
import com.mustafakaplan.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<City> findById(int id) {
        City cityInDB = cityDao.getOne(id);

        if (cityInDB == null) {
            return new ErrorDataResult<>("Data Bulunamadı!");
        }

        return new SuccessDataResult<>(cityInDB, "Data Bulundu");
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<>(cityDao.findAll(), "Data Listelendi");
    }
}
