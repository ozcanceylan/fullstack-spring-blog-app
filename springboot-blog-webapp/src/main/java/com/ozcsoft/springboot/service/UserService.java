package com.ozcsoft.springboot.service;

import com.ozcsoft.springboot.dto.RegistrationDto;
import com.ozcsoft.springboot.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
