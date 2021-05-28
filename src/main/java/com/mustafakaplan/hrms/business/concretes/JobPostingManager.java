package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.JobPostingService;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobDao;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private final JobDao jobDao;

    @Autowired
    public JobPostingManager(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public List<JobPosting> getAll() {
        return jobDao.findAll();
    }
}
