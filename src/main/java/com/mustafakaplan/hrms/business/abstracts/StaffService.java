package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Staff;
import com.mustafakaplan.hrms.entities.dtos.StaffDto;

import java.util.List;

public interface StaffService {

    Result add(StaffDto staff);

    DataResult<List<Staff>> getAll();
}
