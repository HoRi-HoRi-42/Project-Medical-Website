package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/chatBot")
    public String chatBot() {
        return "chatBot";
    }

    @GetMapping("/bmi")
    public String bmi() {
        return "BMI";
    }

    @GetMapping("/programari")
    public String programari() {
        return "programari";
    }

    @GetMapping("/veziprogramari")
    public String veziProgramari() {
        return "veziprogramari";
    }

}

