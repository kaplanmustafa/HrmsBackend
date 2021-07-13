package com.mustafakaplan.hrms.dataAccess.abstracts;

import com.mustafakaplan.hrms.entities.concretes.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);
}
