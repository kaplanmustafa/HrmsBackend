package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployeeService;
import com.mustafakaplan.hrms.business.abstracts.UserService;
import com.mustafakaplan.hrms.core.results.*;
import com.mustafakaplan.hrms.core.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployeeDao;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.concretes.Users;
import com.mustafakaplan.hrms.entities.dtos.EmployeeDto;
import com.mustafakaplan.hrms.mernisReference.PIPKPSPublicSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final MailService mailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_TYPE = "employee";

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, MailService mailService, UserService userService, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.mailService = mailService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result add(EmployeeDto employee) {

        if (!employee.getPassword().equals(employee.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        String errorMessage = "";
        Users emailInDb = userService.findByEmail(employee.getEmail()).getData();
        Employee identityInDb = employeeDao.findByIdentityNumber(employee.getIdentityNumber());

        if (emailInDb != null) {
            errorMessage += "Bu Mail Adresi Başka Bir Kullanıcıya Ait!";
        }

        if (identityInDb != null) {
            errorMessage += "Bu TC No Başka Bir Kullanıcıya Ait!";
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

        Users user = new Users();
        user.setName(employee.getName());
        user.setSurname(employee.getSurname());
        user.setEmail(employee.getEmail());
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        user.setVerifiedEmail(false);
        user.setUserType(USER_TYPE);
        userService.add(user);

        Employee employeeRegister = new Employee();
        employeeRegister.setIdentityNumber(employee.getIdentityNumber());
        employeeRegister.setBirthYear(employee.getBirthYear());
        employeeRegister.setUser(user);
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
