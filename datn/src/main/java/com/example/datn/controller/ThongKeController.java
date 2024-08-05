package com.example.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ThongKeController {

    @GetMapping("/thongke")
    private String view(){
        return "admin/includes/content/thongke/thongke";
    }
}
