package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.JobTypeService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobTypes")
public class JobTypeController {

    private final JobTypeService jobTypeService;

    @Autowired
    public JobTypeController(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @GetMapping("/getById/{id}")
    public DataResult<JobType> findById(@PathVariable int id) {
        return jobTypeService.findById(id);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobType>> getAll() {
        return jobTypeService.getAll();
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody JobType jobType) {
        return jobTypeService.add(jobType);
    }
}
