package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployeeService;
import com.mustafakaplan.hrms.core.utilities.results.*;
import com.mustafakaplan.hrms.core.utilities.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployeeDao;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.dtos.EmployeeDto;
import com.mustafakaplan.hrms.mernisReference.PIPKPSPublicSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final MailService mailService;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, MailService mailService) {
        this.employeeDao = employeeDao;
        this.mailService = mailService;
    }

    @Override
    public Result add(EmployeeDto employee) {

        if (!employee.getPassword().equals(employee.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        String errorMessage = "";
        Employee emailInDb = employeeDao.findByEmail(employee.getEmail());
        Employee identityInDb = employeeDao.findByIdentityNumber(employee.getIdentityNumber());

        if (emailInDb != null) {
            errorMessage += "Bu Mail Adresi Başka Bir Kullanıcıya Ait!";
        }

        if (identityInDb != null) {
            errorMessage += " Bu TC No Başka Bir Kullanıcıya Ait!";
        }

        if (!errorMessage.isEmpty()) {
            return new ErrorResult(errorMessage);
        }

        PIPKPSPublicSoap client = new PIPKPSPublicSoap();
        boolean result = false;
        try {
            result = client.TCKimlikNoDogrula(Long.parseLong(employee.getIdentityNumber()), employee.getName(), employee.getSurname(), employee.getBirthYear());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!result) {
            return new ErrorResult("Lütfen Geçerli Kimlik Bilgileri Giriniz!");
        }

        Employee employeeRegister = new Employee();
        employeeRegister.setName(employee.getName());
        employeeRegister.setSurname(employee.getSurname());
        employeeRegister.setIdentityNumber(employee.getIdentityNumber());
        employeeRegister.setBirthYear(employee.getBirthYear());
        employeeRegister.setEmail(employee.getEmail());
        employeeRegister.setPassword(employee.getPassword());
        employeeRegister.setVerifiedEmail(false);

        employeeDao.save(employeeRegister);
        mailService.sendMailToEmployee(employeeRegister, "Kaydınız başarılı bir şekilde gerçekleşmiştir");

        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(employeeDao.findAll(), "Data Listelendi");
    }

    @Override
    public DataResult<Employee> getEmployeeByIdentityNumber(String identityNumber) {
        return new SuccessDataResult<>(employeeDao.findByIdentityNumber(identityNumber), "Data Listelendi");
    }
}
