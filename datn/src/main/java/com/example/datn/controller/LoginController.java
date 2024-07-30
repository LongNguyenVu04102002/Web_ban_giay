package com.example.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "user/includes/content/login";
    }



    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "login?logout"; // Redirect to login page with a logout message
    }
}
