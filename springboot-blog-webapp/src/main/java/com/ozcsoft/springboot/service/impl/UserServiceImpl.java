package com.ozcsoft.springboot.service.impl;

import com.ozcsoft.springboot.dto.RegistrationDto;
import com.ozcsoft.springboot.entity.Role;
import com.ozcsoft.springboot.entity.User;
import com.ozcsoft.springboot.repository.RoleRepository;
import com.ozcsoft.springboot.repository.UserRepository;
import com.ozcsoft.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user =new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());

        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
