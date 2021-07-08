package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.StaffService;
import com.mustafakaplan.hrms.core.utilities.results.*;
import com.mustafakaplan.hrms.core.utilities.services.abstracts.MailService;
import com.mustafakaplan.hrms.dataAccess.abstracts.StaffDao;
import com.mustafakaplan.hrms.entities.concretes.Staff;
import com.mustafakaplan.hrms.entities.dtos.StaffDto;
import com.mustafakaplan.hrms.mernisReference.PIPKPSPublicSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffManager implements StaffService {

    private final StaffDao staffDao;
    private final MailService mailService;

    @Autowired
    public StaffManager(StaffDao staffDao, MailService mailService) {
        this.staffDao = staffDao;
        this.mailService = mailService;
    }

    @Override
    public Result add(StaffDto staff) {

        if (!staff.getPassword().equals(staff.getPasswordRepeat())) {
            return new ErrorResult("Parolalar Eşleşmiyor!");
        }

        String errorMessage = "";
        Staff emailInDb = staffDao.findByEmail(staff.getEmail());
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

        Staff staffRegister = new Staff();
        staffRegister.setName(staff.getName());
        staffRegister.setSurname(staff.getSurname());
        staffRegister.setIdentityNumber(staff.getIdentityNumber());
        staffRegister.setBirthYear(staff.getBirthYear());
        staffRegister.setEmail(staff.getEmail());
        staffRegister.setPassword(staff.getPassword());
        staffRegister.setVerifiedEmail(false);

        staffDao.save(staffRegister);
        mailService.sendMailToStaff(staffRegister, "Sistem personel kaydı başarılı bir şekilde gerçekleşmiştir");

        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<Staff>> getAll() {
        return new SuccessDataResult<>(staffDao.findAll(), "Data Listelendi");
    }
}
