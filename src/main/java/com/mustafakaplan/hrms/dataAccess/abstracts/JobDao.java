package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<JobPosting, Integer> {
}
