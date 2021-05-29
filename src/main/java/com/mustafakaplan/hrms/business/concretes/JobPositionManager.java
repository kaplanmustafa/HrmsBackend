package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.JobPositionService;
import com.mustafakaplan.hrms.core.utilities.results.*;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobPositionDao;
import com.mustafakaplan.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public Result add(JobPosition jobPosition) {

        JobPosition jobPositionInDb = jobPositionDao.findByJobTitleContainingIgnoreCase(jobPosition.getJobTitle());

        if (jobPositionInDb != null) {
            return new ErrorResult("Bu İş Pozisyonu Zaten Sistemde Kayıtlı!!");
        }

        jobPositionDao.save(jobPosition);
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<>(jobPositionDao.findAll(), "Data Listelendi");
    }

    @Override
    public DataResult<JobPosition> getById(int id) {
        JobPosition jobPositionInDB = jobPositionDao.getOne(id);

        if (jobPositionInDB == null) {
            return new ErrorDataResult<>("Data Bulunamadı!");
        }

        return new SuccessDataResult<>(jobPositionInDB, "Data Bulundu");
    }
}
