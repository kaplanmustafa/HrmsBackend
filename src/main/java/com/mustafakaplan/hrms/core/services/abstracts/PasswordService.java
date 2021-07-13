package com.mustafakaplan.hrms.core.services.abstracts;

public interface PasswordService {

    String encodePassword(String plainPassword);

    Boolean isMatching(String plainPassword, String cryptedPassword);
}
