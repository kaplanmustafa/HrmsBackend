package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.entities.concretes.JobPosting;

import java.util.List;

public interface JobPostingService {

    List<JobPosting> getAll();
}
