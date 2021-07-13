package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.SchoolService;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.core.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.SchoolDao;
import com.mustafakaplan.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolManager implements SchoolService {

    private final SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public Result add(School school) {
        schoolDao.save(school);
        return new SuccessResult("Kayıt Başarılı");
    }
}
