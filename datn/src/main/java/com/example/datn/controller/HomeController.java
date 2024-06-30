package com.example.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "user/home";
    }

    @GetMapping("/shop")
    public String shop(){
        return "user/shop";
    }

    @GetMapping("/about")
    public String about(){
        return "user/about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "user/blog";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/contact";
    }
}
