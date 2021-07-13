package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployerService;
import com.mustafakaplan.hrms.business.abstracts.UserService;
import com.mustafakaplan.hrms.core.results.*;
import com.mustafakaplan.hrms.core.services.abstracts.MailService;
import com.mustafakaplan.hrms.core.services.abstracts.PasswordService;
import com.mustafakaplan.hrms.dataAccess.abstracts.CompanyDao;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployerDao;
import com.mustafakaplan.hrms.entities.concretes.Company;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.concretes.Users;
import com.mustafakaplan.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final MailService mailService;
    private final CompanyDao companyDao;
    private final UserService userService;
    private final PasswordService passwordService;
    private static final String USER_TYPE = "employer";

    @Autowired
    public EmployerManager(EmployerDao employerDao, MailService mailService, CompanyDao companyDao, UserService userService, PasswordService passwordService) {
        this.employerDao = employerDao;
        this.mailService = mailService;
        this.companyDao = companyDao;
        this.userService = userService;
        this.passwordService = passwordService;
    }

    @Override
    public Result add(EmployerDto employer) {

        if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        Users emailInDb = userService.findByEmail(employer.getEmail()).getData();

        if (emailInDb != null) {
            return new ErrorResult("Bu Mail Adresi Başka Bir Şirket Hesabına Ait!");
        }

        String email = employer.getEmail();

        Company company = companyDao.getOne(employer.getCompanyId());

        if (!email.substring(email.indexOf("@") + 1).equals(company.getWebsite().substring(company.getWebsite().indexOf(".") + 1))) {
            return new ErrorResult("Email adresi web sitesi ile aynı domaine sahip olmalıdır!");
        }

        Users user = new Users();
        user.setName(employer.getName());
        user.setSurname(employer.getSurname());
        user.setEmail(employer.getEmail());
        user.setPassword(passwordService.encodePassword(employer.getPassword()));
        user.setVerifiedEmail(false);
        user.setUserType(USER_TYPE);
        userService.add(user);

        Employer employerRegister = new Employer();
        employerRegister.setCompany(company);
        employerRegister.setVerifiedAccount(false);
        employerRegister.setUser(user);
        employerDao.save(employerRegister);

        mailService.sendMailToEmployer(employerRegister, "Şirket personel kaydınız başarılı bir şekilde gerçekleşmiştir");
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "Data Listelendi");
    }
}
