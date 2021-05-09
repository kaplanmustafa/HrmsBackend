package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.JobService;
import com.mustafakaplan.hrms.entities.concretes.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/getall")
    public List<Job> getAll() {
        return jobService.getAll();
    }
}
