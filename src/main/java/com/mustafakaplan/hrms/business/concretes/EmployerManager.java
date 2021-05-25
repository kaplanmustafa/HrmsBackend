package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployerService;
import com.mustafakaplan.hrms.core.utilities.results.ErrorResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.core.utilities.results.SuccessResult;
import com.mustafakaplan.hrms.core.utilities.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployerDao;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.concretes.vm.EmployerVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final MailService mailService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, MailService mailService) {
        this.employerDao = employerDao;
        this.mailService = mailService;
    }

    @Override
    public Result add(EmployerVM employer) {

        if (employer.getCompanyName() == null || employer.getWebsite() == null || employer.getEmail() == null || employer.getPhone() == null
                || employer.getPassword() == null || employer.getPasswordRepeat() == null) {
            return new ErrorResult("Lütfen Tüm Alanları Doldurunuz!");
        }

        if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        Employee emailInDb = employerDao.findByEmail(employer.getEmail());

        if (emailInDb != null) {
            return new ErrorResult("Bu Mail Adresi Başka Bir Şirket Hesabına Ait!");
        }

        String email = employer.getEmail();
        String website = employer.getWebsite();

        if (!email.substring(email.indexOf("@") + 1).equals(website.substring(website.indexOf(".") + 1))) {
            return new ErrorResult("Email adresi web sitesi ile aynı domaine sahip olmalıdır!");
        }

        Employer employerRegister = new Employer();
        employerRegister.setCompanyName(employer.getCompanyName());
        employerRegister.setWebsite(employer.getWebsite());
        employerRegister.setEmail(employer.getEmail());
        employerRegister.setPhone(employer.getPhone());
        employerRegister.setPassword(employer.getPassword());
        employerRegister.setVerifiedAccount(false);
        employerRegister.setVerifiedEmail(false);

        employerDao.save(employerRegister);
        mailService.sendMailToCompany(employerRegister, "Şirket kaydınız başarılı bir şekilde gerçekleşmiştir");

        return new SuccessResult("Kayıt Başarılı");
    }
}
