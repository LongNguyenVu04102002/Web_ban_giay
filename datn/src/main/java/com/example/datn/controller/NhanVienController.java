package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.Impl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/taikhoan")
public class NhanVienController {

    @Autowired
    NhanVienServiceImpl nhanVienService;

    @GetMapping("/nhanvien")
    public String show(Model model) {
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanVienList", nhanVienList);
        return "admin/includes/content/nhanvien/home";

    }

    @GetMapping("/nhanvien/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "admin/includes/content/nhanvien/form";
    }

    @GetMapping("/nhanvien/form")
    public String viewAdd(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "admin/includes/content/nhanvien/form";
    }

    @PostMapping("/nhanvien/save")
    public String save(NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return "redirect:/admin/taikhoan/nhanvien";
    }


}