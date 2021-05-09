package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.JobService;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobDao;
import com.mustafakaplan.hrms.entities.concretes.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobManager implements JobService {

    private final JobDao jobDao;

    @Autowired
    public JobManager(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public List<Job> getAll() {
        return jobDao.findAll();
    }
}
