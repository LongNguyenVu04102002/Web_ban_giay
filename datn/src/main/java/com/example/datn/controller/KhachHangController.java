package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/khachhang")
    public String listKhachHang(Model model) {
        model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
        return "khachhang"; // Trả về tên của view (khachhang.jsp)
    }

    @GetMapping("/add")
    public String addKhachHangForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khachhang/add";
    }

    @PostMapping("/add")
    public String addKhachHang(@ModelAttribute KhachHang khachHang) {
        khachHangService.saveKhachHang(khachHang);
        return "redirect:/khachhang";
    }

    @GetMapping("/edit/{id}")
    public String editKhachHangForm(@PathVariable Long id, Model model) {
        Optional<KhachHang> khachHang = khachHangService.getKhachHangById(id);
        if (khachHang.isPresent()) {
            model.addAttribute("khachHang", khachHang.get());
            return "khachhang/edit";
        } else {
            return "404";
        }
    }

    @PostMapping("/edit/{id}")
    public String editKhachHang(@PathVariable Long id, @ModelAttribute KhachHang khachHang) {
        khachHang.setKhachHangId(id);
        khachHangService.saveKhachHang(khachHang);
        return "redirect:/khachhang";
    }

    @GetMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable Long id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/khachhang";
    }
   
}
