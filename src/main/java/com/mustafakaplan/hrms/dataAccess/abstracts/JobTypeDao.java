package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeDao extends JpaRepository<JobType, Integer> {

}
