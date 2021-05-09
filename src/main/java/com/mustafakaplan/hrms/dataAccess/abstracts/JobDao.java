package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job, Integer> {
}
