package com.mustafakaplan.hrms.core.utilities.services.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Employee;

public interface MailService {

    boolean sendMailToEmployee(Employee employee, String content);

    //boolean sendMailToCorporate(Employee employee, String content);
}
