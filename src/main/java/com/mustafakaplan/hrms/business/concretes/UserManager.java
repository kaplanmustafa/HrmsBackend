package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.UserService;
import com.mustafakaplan.hrms.core.results.*;
import com.mustafakaplan.hrms.dataAccess.abstracts.UserDao;
import com.mustafakaplan.hrms.entities.concretes.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(Users user) {
        userDao.save(user);
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<Users> findByEmail(String email) {
        Users userInDB = userDao.findByEmail(email);

        if (userInDB == null) {
            return new ErrorDataResult<>("Data Bulunamadı!");
        }

        return new SuccessDataResult<>(userInDB, "Data Bulundu");
    }
}
