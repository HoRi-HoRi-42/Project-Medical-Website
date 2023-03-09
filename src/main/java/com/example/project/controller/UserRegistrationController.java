package com.example.project.controller;

import com.example.project.dto.UserRegistrationDto;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private UserRepository userRepository;

    public UserRegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        User user = userRepository.findByEmail(registrationDto.getEmail());
        if(user != null) {
            return "redirect:/registration?error";
        }
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}

