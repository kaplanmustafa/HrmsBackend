package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.entities.dtos.AuthResponseDto;
import com.mustafakaplan.hrms.entities.dtos.CredentialsDto;

public interface AuthService {

    DataResult<AuthResponseDto> authenticate(CredentialsDto credentials);
}
