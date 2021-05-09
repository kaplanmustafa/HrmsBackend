package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Job;

import java.util.List;

public interface JobService {

    List<Job> getAll();
}
