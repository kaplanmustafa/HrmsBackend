package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Cv;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvDao extends JpaRepository<Cv, Integer> {

    Cv findByEmployee(Employee employee);
}
