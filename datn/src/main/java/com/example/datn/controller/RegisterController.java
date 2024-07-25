package com.example.datn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.datn.service.Impl.KhachHangServiceImpl;

@Controller
public class RegisterController {


    @Autowired
    private KhachHangServiceImpl khachHangService;

    @PostMapping("/register")
    public String registerKhachHang(@RequestParam String hoTen,
                                    @RequestParam String sdt,
                                    @RequestParam String email,
                                    @RequestParam String password) {
        try {
            khachHangService.registerNewKhachHang(email, password, hoTen, sdt);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }

    @GetMapping("/register")
    public String  register(){

            return "user/includes/content/register";
        }


}