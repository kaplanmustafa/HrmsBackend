package com.mustafakaplan.hrms.core.utilities.services.concretes;

import com.mustafakaplan.hrms.core.utilities.services.abstracts.MailService;
import com.mustafakaplan.hrms.entities.concretes.Employee;
import org.springframework.stereotype.Service;

@Service
public class MailManager implements MailService {

    @Override
    public boolean sendMailToEmployee(Employee employee, String content) {
        //TODO send mail
        return true;
    }
}
