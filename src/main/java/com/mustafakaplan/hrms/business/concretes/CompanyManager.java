package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.CompanyService;
import com.mustafakaplan.hrms.core.results.*;
import com.mustafakaplan.hrms.core.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.CompanyDao;
import com.mustafakaplan.hrms.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyManager implements CompanyService {

    private final CompanyDao companyDao;
    private final MailService mailService;

    @Autowired
    public CompanyManager(CompanyDao companyDao, MailService mailService) {
        this.companyDao = companyDao;
        this.mailService = mailService;
    }

    @Override
    public Result add(Company company) {
        Company companyInDB = companyDao.findByWebsite(company.getWebsite());

        if (company != null) {
            return new ErrorResult(company.getWebsite() + " web adresi başka bir şirkete ait!");
        }

        companyDao.save(company);
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Company>> getAll() {
        return new SuccessDataResult<>(companyDao.findAll(), "Data Listelendi");
    }
}
