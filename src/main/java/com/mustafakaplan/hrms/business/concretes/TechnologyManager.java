package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.TechnologyService;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.core.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.TechnologyDao;
import com.mustafakaplan.hrms.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyManager implements TechnologyService {

    private final TechnologyDao technologyDao;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao) {
        this.technologyDao = technologyDao;
    }

    @Override
    public Result add(Technology technology) {
        technologyDao.save(technology);
        return new SuccessResult("Kayıt Başarılı");
    }
}
