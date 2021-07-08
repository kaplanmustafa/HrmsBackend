package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployerService;
import com.mustafakaplan.hrms.core.utilities.results.*;
import com.mustafakaplan.hrms.core.utilities.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.CompanyDao;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployerDao;
import com.mustafakaplan.hrms.entities.concretes.Company;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final MailService mailService;
    private final CompanyDao companyDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao, MailService mailService, CompanyDao companyDao) {
        this.employerDao = employerDao;
        this.mailService = mailService;
        this.companyDao = companyDao;
    }

    @Override
    public Result add(EmployerDto employer) {

        if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        Employer emailInDb = employerDao.findByEmail(employer.getEmail());

        if (emailInDb != null) {
            return new ErrorResult("Bu Mail Adresi Başka Bir Şirket Hesabına Ait!");
        }

        String email = employer.getEmail();

        Company company = companyDao.getOne(employer.getCompanyId());

        if (!email.substring(email.indexOf("@") + 1).equals(company.getWebsite().substring(company.getWebsite().indexOf(".") + 1))) {
            return new ErrorResult("Email adresi web sitesi ile aynı domaine sahip olmalıdır!");
        }

        Employer employerRegister = new Employer();
        employerRegister.setEmail(employer.getEmail());
        employerRegister.setCompany(company);
        employerRegister.setPassword(employer.getPassword());
        employerRegister.setVerifiedAccount(false);
        employerRegister.setVerifiedEmail(false);

        employerDao.save(employerRegister);
        mailService.sendMailToEmployer(employerRegister, "Şirket personel kaydınız başarılı bir şekilde gerçekleşmiştir");

        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "Data Listelendi");
    }

    @Override
    public DataResult<Employer> findByEmail(String email) {
        Employer employerInDB = employerDao.findByEmail(email);

        if (employerInDB == null) {
            return new ErrorDataResult<>("Data Bulunamadı!");
        }

        return new SuccessDataResult<>(employerInDB, "Data Bulundu");
    }
}
