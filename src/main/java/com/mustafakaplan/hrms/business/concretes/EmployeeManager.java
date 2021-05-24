package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.EmployeeService;
import com.mustafakaplan.hrms.core.utilities.results.ErrorResult;
import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.core.utilities.results.SuccessResult;
import com.mustafakaplan.hrms.dataAccess.abstracts.EmployeeDao;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.concretes.vm.EmployeeVM;
import com.mustafakaplan.hrms.mernisReference.EROKPSPublicSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Result add(EmployeeVM employee) {

        if (employee.getName() == null || employee.getSurname() == null || employee.getIdentityNumber() == null || employee.getBirthYear() == 0
                || employee.getEmail() == null || employee.getPassword() == null || employee.getPasswordRepeat() == null) {
            return new ErrorResult("Lütfen Tüm Alanları Doldurunuz!");
        }

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

        EROKPSPublicSoap client = new EROKPSPublicSoap();
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
        employeeRegister.setVerified(false);
        employeeDao.save(employeeRegister);

        return new SuccessResult("Kayıt Başarılı");
    }
}
