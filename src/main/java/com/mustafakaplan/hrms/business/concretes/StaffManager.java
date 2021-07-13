package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.StaffService;
import com.mustafakaplan.hrms.business.abstracts.UserService;
import com.mustafakaplan.hrms.core.results.*;
import com.mustafakaplan.hrms.core.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.StaffDao;
import com.mustafakaplan.hrms.entities.concretes.Staff;
import com.mustafakaplan.hrms.entities.concretes.Users;
import com.mustafakaplan.hrms.entities.dtos.StaffDto;
import com.mustafakaplan.hrms.mernisReference.PIPKPSPublicSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffManager implements StaffService {

    private final StaffDao staffDao;
    private final MailService mailService;
    private final UserService userService;
    private static final String USER_TYPE = "staff";

    @Autowired
    public StaffManager(StaffDao staffDao, MailService mailService, UserService userService) {
        this.staffDao = staffDao;
        this.mailService = mailService;
        this.userService = userService;
    }

    @Override
    public Result add(StaffDto staff) {

        if (!staff.getPassword().equals(staff.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        String errorMessage = "";
        Users emailInDb = userService.findByEmail(staff.getEmail()).getData();
        Staff identityInDb = staffDao.findByIdentityNumber(staff.getIdentityNumber());

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
            result = client.TCKimlikNoDogrula(Long.parseLong(staff.getIdentityNumber()), staff.getName(), staff.getSurname(), staff.getBirthYear());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!result) {
            return new ErrorResult("Lütfen Geçerli Kimlik Bilgileri Giriniz!");
        }

        Users user = new Users();
        user.setName(staff.getName());
        user.setSurname(staff.getSurname());
        user.setEmail(staff.getEmail());
        user.setPassword(staff.getPassword());
        user.setVerifiedEmail(false);
        user.setUserType(USER_TYPE);
        userService.add(user);

        Staff staffRegister = new Staff();
        staffRegister.setIdentityNumber(staff.getIdentityNumber());
        staffRegister.setBirthYear(staff.getBirthYear());
        staffRegister.setUser(user);
        staffDao.save(staffRegister);

        mailService.sendMailToStaff(staffRegister, "Sistem personel kaydı başarılı bir şekilde gerçekleşmiştir");
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Staff>> getAll() {
        return new SuccessDataResult<>(staffDao.findAll(), "Data Listelendi");
    }
}
