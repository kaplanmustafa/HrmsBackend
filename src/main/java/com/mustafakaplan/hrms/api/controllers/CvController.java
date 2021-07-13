package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.CvService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Cv;
import com.mustafakaplan.hrms.entities.dtos.CvSubmitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cvs")
public class CvController {

    private final CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/getCvOfUser/{identityNumber}")
    public DataResult<Cv> getCvOfUser(@PathVariable String identityNumber) {
        return cvService.getCvOfUser(identityNumber);
    }

    @PostMapping("/add/{identityNumber}")
    public Result add(@Valid @RequestBody CvSubmitDto cv, @PathVariable String identityNumber) {
        return cvService.add(cv, identityNumber);
    }
}
