package com.mustafakaplan.hrms.core.utilities.services.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Employee;
import com.mustafakaplan.hrms.entities.concretes.Employer;
import com.mustafakaplan.hrms.entities.concretes.Staff;

public interface MailService {

    boolean sendMailToEmployee(Employee employee, String content);

    boolean sendMailToEmployer(Employer employer, String content);

    boolean sendMailToStaff(Staff staff, String content);
}
