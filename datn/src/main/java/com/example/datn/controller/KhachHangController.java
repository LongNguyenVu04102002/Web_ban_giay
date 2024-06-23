package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String listKhachHang(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "3") int size) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber() + 1); // Vị trí trang hiện tại
        model.addAttribute("totalPages", khachHangPage.getTotalPages()); // Tổng số trang
        model.addAttribute("khachHang", new KhachHang()); // Thêm đối tượng khachHang vào model
        return "left-menu"; // Trả về tên của view (khachhang.jsp)
    }


    @GetMapping("/add")
    public String addKhachHangForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khachhang/add";
    }

    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/khachhang";
    }

    @PostMapping("/saveKhachHang")
    public String saveKhachHang(@ModelAttribute("khachHang") KhachHang khachHang) {
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
