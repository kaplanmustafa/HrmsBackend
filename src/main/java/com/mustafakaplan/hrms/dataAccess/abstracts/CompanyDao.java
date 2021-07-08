package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {

    Company findByWebsite(String website);
}
