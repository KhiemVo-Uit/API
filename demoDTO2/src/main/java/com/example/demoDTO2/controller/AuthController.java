package com.example.demoDTO2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Thymeleaf sẽ tìm file login.html trong templates/
    }
}

