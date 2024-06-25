package com.example.datn.controller;


import com.example.datn.entity.*;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SanPhamChiTietController {

    @Autowired
    private IService<SanPhamChiTiet> spctService;

    @Autowired
    private IService<SanPham> sanPhamService;

    @Autowired
    private IService<KichThuoc> kichThuocService;

    @Autowired
    private IService<MauSac> mauSacService;

    @Autowired
    private IService<DotGiamGia> dotGiamGiaService;

    @GetMapping("/san-pham-chi-tiet")
    public String show(@ModelAttribute("spct") SanPhamChiTiet spct, @RequestParam(name = "p", defaultValue = "0") Integer p, Model model) {
        Page<SanPhamChiTiet> page = spctService.pagination(p, 3);
        model.addAttribute("lst", page);
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstDotGiamGia", dotGiamGiaService.getAll());
        spct.setTrangThai(true);
        return "san-pham-chi-tiet";
    }

    @PostMapping("/san-pham-chi-tiet/add")
    public String add(@ModelAttribute("spct") SanPhamChiTiet spct, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("spct", new SanPhamChiTiet());
        spctService.addOrUpdate(spct);
        Page<SanPhamChiTiet> page = spctService.pagination(p, 3);
        model.addAttribute("lst, page");
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstDotGiamGia", dotGiamGiaService.getAll());
        return "redirect:/san-pham-chi-tiet";
    }

    @GetMapping("/san-pham-chi-tiet/detail/{id}")
    public String detail(@ModelAttribute("spct") Optional<SanPhamChiTiet> spct, @PathVariable("id") Long id, Model model, @RequestParam(value = "p", defaultValue = "0") Integer p) {
        model.addAttribute("spct", spctService.getOne(id));
        Page<SanPhamChiTiet> page = spctService.pagination(p, 3);
        model.addAttribute("lst", page);
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstDotGiamGia", dotGiamGiaService.getAll());
        return "san-pham-chi-tiet";
    }

    @PostMapping("/san-pham-chi-tiet/update/{id}")
    public String update(@ModelAttribute("spct") SanPhamChiTiet spct, @PathVariable("id") Long id, Model model, @RequestParam(value = "p", defaultValue = "0") Integer p) {
        model.addAttribute("spct", new SanPhamChiTiet());
        spctService.addOrUpdate(spct);
        Page<SanPhamChiTiet> page = spctService.pagination(p, 3);
        model.addAttribute("lst", page);
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstDotGiamGia", dotGiamGiaService.getAll());
        return "redirect:/san-pham-chi-tiet";
    }

    @GetMapping("/san-pham-chi-tiet/remove/{id}")
    public String remvoe(@ModelAttribute("spct") SanPhamChiTiet sanPhamChiTiet, Model model, @PathVariable("id") Long id, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        spctService.remove(id);
        Page<SanPhamChiTiet> page = spctService.pagination(p, 3);
        model.addAttribute("lst", page);
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstDotGiamGia", dotGiamGiaService.getAll());
        return "redirect:/san-pham-chi-tiet";
    }

}
