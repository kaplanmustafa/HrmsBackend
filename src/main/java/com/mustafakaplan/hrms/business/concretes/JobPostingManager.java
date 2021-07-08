package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.*;
import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.core.utilities.results.SuccessDataResult;
import com.mustafakaplan.hrms.core.utilities.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.JobPostingDao;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import com.mustafakaplan.hrms.entities.dtos.JobPostingDto;
import com.mustafakaplan.hrms.entities.dtos.JobPostingSubmitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private final JobPostingDao jobPostingDao;
    private final JobPositionService jobPositionService;
    private final EmployerService employerService;
    private final CityService cityService;
    private final JobTypeService jobTypeService;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao, JobPositionService jobPositionService, EmployerService employerService, CityService cityService, JobTypeService jobTypeService) {
        this.jobPostingDao = jobPostingDao;
        this.jobPositionService = jobPositionService;
        this.employerService = employerService;
        this.cityService = cityService;
        this.jobTypeService = jobTypeService;
    }

    @Override
    public DataResult<List<JobPosting>> getAll() {
        return new SuccessDataResult<>(jobPostingDao.findAll(), "Data Listelendi");
    }

    @Override
    public Result add(JobPostingSubmitDto submitVM) {
        JobPosting jobPosting = new JobPosting();

        jobPosting.setDescription(submitVM.getDescription());
        jobPosting.setMinSalary(submitVM.getMinSalary());
        jobPosting.setMaxSalary(submitVM.getMaxSalary());
        jobPosting.setStartDate(new Date());
        jobPosting.setEndDate(submitVM.getEndDate());
        jobPosting.setNumberOfEmployees(submitVM.getNumberOfEmployees());
        jobPosting.setJobPosition(jobPositionService.getById(submitVM.getJobPositionId()).getData());
        jobPosting.setCompany(employerService.findByEmail(submitVM.getEmployerEmail()).getData().getCompany());
        jobPosting.setCity(cityService.findById(submitVM.getCityId()).getData());
        jobPosting.setJobType(jobTypeService.findById(submitVM.getJobTypeId()).getData());
        jobPosting.setRemote(submitVM.getIsRemote() == 1 ? true : false);
        jobPosting.setActive(false);
        jobPosting.setApproved(false);

        jobPostingDao.save(jobPosting);
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<JobPostingDto>> getAllActivePosting() {
        return new SuccessDataResult<>(jobPostingDao.findAllByIsActiveTrue(), "Data Listelendi");
    }

    @Override
    public DataResult<List<JobPostingDto>> getAllActivePostingByDateDesc() {
        return new SuccessDataResult<>(jobPostingDao.findAllByIsActiveTrueOrderByStartDateDesc(), "Data Listelendi");
    }

    @Override
    public DataResult<List<JobPostingDto>> getAllActivePostingByDateAsc() {
        return new SuccessDataResult<>(jobPostingDao.findAllByIsActiveTrueOrderByStartDateAsc(), "Data Listelendi");
    }

    @Override
    public DataResult<List<JobPostingDto>> getAllActivePostingByCompany(String companyWebsite) {
        return new SuccessDataResult<>(jobPostingDao.getActivePostingsWithDetails(companyWebsite), "Data Listelendi");
    }
}
