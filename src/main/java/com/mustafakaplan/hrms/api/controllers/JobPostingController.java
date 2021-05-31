package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.JobPostingService;
import com.mustafakaplan.hrms.core.utilities.results.DataResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobPosting;
import com.mustafakaplan.hrms.entities.dtos.JobPostingSubmitDto;
import com.mustafakaplan.hrms.entities.dtos.JobPostingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPosting>> getAll() {
        return jobPostingService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPostingSubmitDto jobPosting) {
        return jobPostingService.add(jobPosting);
    }

    @GetMapping("/getAllActivePosting")
    public DataResult<List<JobPostingDto>> getAllActivePosting() {
        return jobPostingService.getAllActivePosting();
    }

    @GetMapping("/getAllActivePostingByDateDesc")
    public DataResult<List<JobPostingDto>> getAllActivePostingByDateDesc() {
        return jobPostingService.getAllActivePostingByDateDesc();
    }

    @GetMapping("/getAllActivePostingByDateAsc")
    public DataResult<List<JobPostingDto>> getAllActivePostingByDateAsc() {
        return jobPostingService.getAllActivePostingByDateAsc();
    }

    @GetMapping("/getAllActivePostingByCompany")
    public DataResult<List<JobPostingDto>> getAllActivePostingByCompany(@RequestParam String companyWebsite) {
        return jobPostingService.getAllActivePostingByCompany(companyWebsite);
    }
}
