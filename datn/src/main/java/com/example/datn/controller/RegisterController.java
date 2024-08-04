package com.example.datn.controller;

import com.example.datn.entity.KhachHang;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {


    @Autowired
    private KhachHangServiceImpl khachHangService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "user/includes/content/register";
    }

    @PostMapping("/register")
    public String registerEmployee(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String hoTen,
                                   @RequestParam String sdt,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngaySinh,
                                   @RequestParam(required = false) Boolean gioiTinh,
                                   @RequestParam(required = false) Boolean trangThai,
                                   Model model) {
        // Thiết lập giá trị mặc định nếu các trường null
        if (ngaySinh == null) {
            ngaySinh = LocalDate.now().minusYears(1); // Ngày sinh mặc định là ngày hôm qua
        }
        if (gioiTinh == null) {
            gioiTinh = true; // Giới tính mặc định là true
        }
        if (trangThai == null) {
            trangThai = true; // Trạng thái mặc định là true
        }

        try {
            khachHangService.registerNewKhachHang(email, password, hoTen, sdt, ngaySinh, gioiTinh, trangThai);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "user/includes/content/login";
        }
    }

}
