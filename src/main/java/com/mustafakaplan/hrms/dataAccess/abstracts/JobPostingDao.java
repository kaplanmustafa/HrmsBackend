package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import com.mustafakaplan.hrms.entities.dtos.JobPostingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {

    @Query("Select new com.mustafakaplan.hrms.entities.dtos.JobPostingDto(e.companyName, p.jobTitle, j.numberOfEmployees, j.startDate, j.endDate) From JobPosting j Inner Join j.employer e Inner Join j.jobPosition p " +
            "where j.isActive=true")
    List<JobPostingDto> findAllByIsActiveTrue();

    @Query("Select new com.mustafakaplan.hrms.entities.dtos.JobPostingDto(e.companyName, p.jobTitle, j.numberOfEmployees, j.startDate, j.endDate) From JobPosting j Inner Join j.employer e Inner Join j.jobPosition p " +
            "where j.isActive=true order by j.startDate desc")
    List<JobPostingDto> findAllByIsActiveTrueOrderByStartDateDesc();

    @Query("Select new com.mustafakaplan.hrms.entities.dtos.JobPostingDto(e.companyName, p.jobTitle, j.numberOfEmployees, j.startDate, j.endDate) From JobPosting j Inner Join j.employer e Inner Join j.jobPosition p " +
            "where j.isActive=true order by j.startDate asc ")
    List<JobPostingDto> findAllByIsActiveTrueOrderByStartDateAsc();

    @Query("Select new com.mustafakaplan.hrms.entities.dtos.JobPostingDto(e.companyName, p.jobTitle, j.numberOfEmployees, j.startDate, j.endDate) From JobPosting j Inner Join j.employer e Inner Join j.jobPosition p " +
            "where e.website=:website and j.isActive=true")
    List<JobPostingDto> getActivePostingsWithDetails(String website);
}

