package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.CityService;
import com.mustafakaplan.hrms.business.abstracts.EmployerService;
import com.mustafakaplan.hrms.business.abstracts.JobPositionService;
import com.mustafakaplan.hrms.business.abstracts.JobPostingService;
import com.mustafakaplan.hrms.core.utilities.results.*;
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

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao, JobPositionService jobPositionService, EmployerService employerService, CityService cityService) {
        this.jobPostingDao = jobPostingDao;
        this.jobPositionService = jobPositionService;
        this.employerService = employerService;
        this.cityService = cityService;
    }

    @Override
    public DataResult<List<JobPosting>> getAll() {
        return new SuccessDataResult<>(jobPostingDao.findAll(), "Data Listelendi");
    }

    @Override
    public Result add(JobPostingSubmitDto submitVM) {

        if (submitVM.getJobPositionId() == 0 || submitVM.getDescription() == null || submitVM.getCityId() == 0 || submitVM.getNumberOfEmployees() < 1) {
            return new ErrorResult("Lütfen Zorunlu Alanları Doldurunuz!");
        }

        JobPosting jobPosting = new JobPosting();

        jobPosting.setDescription(submitVM.getDescription());
        jobPosting.setMinSalary(submitVM.getMinSalary());
        jobPosting.setMaxSalary(submitVM.getMaxSalary());
        jobPosting.setStartDate(new Date());
        jobPosting.setEndDate(submitVM.getEndDate());
        jobPosting.setNumberOfEmployees(submitVM.getNumberOfEmployees());
        jobPosting.setJobPosition(jobPositionService.getById(submitVM.getJobPositionId()).getData());
        jobPosting.setEmployer(employerService.findByEmail(submitVM.getEmployerEmail()).getData());
        jobPosting.setCity(cityService.findById(submitVM.getCityId()).getData());
        jobPosting.setActive(true);

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
