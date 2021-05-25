package com.mustafakaplan.hrms.core.utilities.services.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.concretes.Employer;

public interface MailService {

    boolean sendMailToEmployee(Employee employee, String content);

    boolean sendMailToCompany(Employer employer, String content);
}
