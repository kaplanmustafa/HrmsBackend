package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.CompanyService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody Company company) {
        return companyService.add(company);
    }

    @GetMapping("/getAll")
    public DataResult<List<Company>> getAll() {
        return companyService.getAll();
    }
}
