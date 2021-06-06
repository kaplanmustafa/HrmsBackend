package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.ForeignLanguageService;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.core.utilities.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.ForeignLanguageDao;
import com.mustafakaplan.hrms.entities.concretes.ForeignLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

    private final ForeignLanguageDao foreignLanguageDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao) {
        this.foreignLanguageDao = foreignLanguageDao;
    }

    @Override
    public Result add(ForeignLanguage foreignLanguage) {
        foreignLanguageDao.save(foreignLanguage);
        return new SuccessResult("Kayıt Başarılı");
    }
}
