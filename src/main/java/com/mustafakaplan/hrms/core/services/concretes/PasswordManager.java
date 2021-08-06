package com.mustafakaplan.hrms.core.services.concretes;

import com.mustafakaplan.hrms.core.services.abstracts.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordManager implements PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PasswordManager() {
        this.passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public Boolean isMatching(String plainPassword, String cryptedPassword) {
        return passwordEncoder.matches(plainPassword, cryptedPassword);
    }
}
