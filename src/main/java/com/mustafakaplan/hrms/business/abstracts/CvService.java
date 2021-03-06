package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Cv;
import com.mustafakaplan.hrms.entities.dtos.CvSubmitDto;

public interface CvService {

    Result add(CvSubmitDto cv, String identityNumber);

    DataResult<Cv> getCvOfUser(String identityNumber);
}
