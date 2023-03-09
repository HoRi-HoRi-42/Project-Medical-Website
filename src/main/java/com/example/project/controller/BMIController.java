package com.example.project.controller;

import com.example.project.dto.UserMailDto;
import com.example.project.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BMIController {
    @ModelAttribute("user")
    public UserMailDto userMailDto() {
        return new UserMailDto();
    }

    @RequestMapping("/BMI")
    @PostMapping
    public void Bmi(@ModelAttribute("user") UserMailDto userMailDto) {
        System.out.println("aiccciciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        System.out.println(userMailDto.getEmail());
        EmailService.run(userMailDto.getEmail());
    }
}
