package com.mustafakaplan.hrms.api.controllers;

import com.mustafakaplan.hrms.business.abstracts.StaffService;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Staff;
import com.mustafakaplan.hrms.entities.dtos.StaffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody StaffDto staff) {
        return staffService.add(staff);
    }

    @GetMapping("/getAll")
    public DataResult<List<Staff>> getAll() {
        return staffService.getAll();
    }
}
