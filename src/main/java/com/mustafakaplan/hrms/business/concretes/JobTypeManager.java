package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.JobTypeService;
import com.mustafakaplan.hrms.core.utilities.results.*;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobTypeDao;
import com.mustafakaplan.hrms.entities.concretes.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeManager implements JobTypeService {

    private final JobTypeDao jobTypeDao;

    @Autowired
    public JobTypeManager(JobTypeDao jobTypeDao) {
        this.jobTypeDao = jobTypeDao;
    }

    @Override
    public DataResult<JobType> findById(int id) {
        JobType jobTypeInDB = jobTypeDao.getOne(id);

        if (jobTypeInDB == null) {
            return new ErrorDataResult<>("Data Bulunamadı!");
        }

        return new SuccessDataResult<>(jobTypeInDB, "Data Bulundu");
    }

    @Override
    public DataResult<List<JobType>> getAll() {
        return new SuccessDataResult<>(jobTypeDao.findAll(), "Data Listelendi");
    }

    @Override
    public Result add(JobType jobTypeSubmit) {
        JobType jobType = new JobType();
        jobType.setJobType(jobTypeSubmit.getJobType());
        jobTypeDao.save(jobType);

        return new SuccessResult("Kayıt Başarılı");
    }
}
