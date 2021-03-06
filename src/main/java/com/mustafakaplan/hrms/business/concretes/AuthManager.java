package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.AuthService;
import com.mustafakaplan.hrms.business.abstracts.UserService;
import com.mustafakaplan.hrms.core.exceptions.AuthException;
import com.mustafakaplan.hrms.core.jwt.JwtUtil;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.SuccessDataResult;
import com.mustafakaplan.hrms.core.services.abstracts.PasswordService;
import com.mustafakaplan.hrms.entities.concretes.Users;
import com.mustafakaplan.hrms.entities.dtos.AuthResponseDto;
import com.mustafakaplan.hrms.entities.dtos.CredentialsDto;
import com.mustafakaplan.hrms.entities.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordService passwordService;

    @Autowired
    public AuthManager(UserService userService, JwtUtil jwtUtil, PasswordService passwordService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordService = passwordService;
    }

    @Override
    public DataResult<AuthResponseDto> authenticate(CredentialsDto credentials) {
        Users inDB = userService.findByEmail(credentials.getEmail()).getData();

        if (inDB == null) {
            throw new AuthException();
        }

        if (!credentials.getUserType().equals(inDB.getUserType())) {
            throw new AuthException();
        }

        if (!passwordService.isMatching(credentials.getPassword(), inDB.getPassword())) {
            throw new AuthException();
        }

        final String jwt = jwtUtil.generateToken(credentials.getEmail());

        UserDto userVM = new UserDto(inDB.getEmail(), inDB.getName(), inDB.getSurname());

        AuthResponseDto response = new AuthResponseDto();
        response.setUser(userVM);
        response.setToken(jwt);
        response.setUserType(credentials.getUserType());

        return new SuccessDataResult<>(response, "Giri?? Ba??ar??l??");
    }
}
