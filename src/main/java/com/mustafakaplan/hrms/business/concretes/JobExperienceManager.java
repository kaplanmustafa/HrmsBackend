package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.JobExperienceService;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.core.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobExperienceDao;
import com.mustafakaplan.hrms.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    @Override
    public Result add(JobExperience jobExperience) {
        jobExperienceDao.save(jobExperience);
        return new SuccessResult("Kayıt Başarılı");
    }
}
