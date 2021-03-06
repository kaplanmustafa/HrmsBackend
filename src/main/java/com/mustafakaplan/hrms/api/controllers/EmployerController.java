package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.EmployerService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody EmployerDto employer) {
        return employerService.add(employer);
    }

    @GetMapping("/getAll")
    public DataResult<List<Employer>> getAll() {
        return employerService.getAll();
    }
}
