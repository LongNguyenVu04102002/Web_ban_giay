package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.service.impl.KichThuocServiceImpl;
import com.example.datn.service.impl.MauSacServiceImpl;
import com.example.datn.service.impl.SanPhamChiTietServiceImpl;
import com.example.datn.service.impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    @Autowired
    private MauSacServiceImpl mauSacService;

    @GetMapping("/bienthegiay")
    public String show(Model model) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.getAll();
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "admin/includes/content/sanpham/bienthegiay/home";
    }

    @GetMapping("/bienthegiay/form")
    public String form(Model model) {
        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
        model.addAttribute("spct", new SanPhamChiTiet());
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return getString(model);
    }

    @GetMapping("/bienthegiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getById(id);
        model.addAttribute("spct", sanPhamChiTiet);
        return getString(model);
    }

    @PostMapping("/bienthegiay/save")
    public String save(@ModelAttribute("sanPhamChiTietList") SanPhamChiTietResponse sanPhamChiTietResponse) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietResponse.getSanPhamChiTietList();
        sanPhamChiTietService.save(sanPhamChiTietList);
        return "redirect:/admin/sanpham/bienthegiay";
    }

    private String getString(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lsMauSac", mauSacService.getAll());
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        return "admin/includes/content/sanpham/bienthegiay/form";
    }

    @PostMapping("/bienthegiay/update/{id}")
    public String update(@PathVariable Long id) {
        sanPhamChiTietService.update(id);
        return "redirect:/admin/sanpham/bienthegiay";
    }
}
