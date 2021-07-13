package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.entities.concretes.Users;

public interface UserService {

    Result add(Users user);

    DataResult<Users> findByEmail(String email);
}
