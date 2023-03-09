package com.example.project.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.project.model.User;
import com.example.project.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
