package com.example.datn.controller;

import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @GetMapping("/detail/{id}")
    public String getHoaDonById(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        List<SanPhamChiTiet> uniqueSizes = sanPhamService.uniqueSizes(sanPham.getSanPhamChiTietList());
        List<SanPhamChiTiet> uniqueColors = sanPhamService.uniqueColor(sanPham.getSanPhamChiTietList());
        model.addAttribute("selectedSizeId", null);
        model.addAttribute("selectedColorId", null);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("uniqueSizes", uniqueSizes);
        model.addAttribute("uniqueColors", uniqueColors);
        return "user/includes/content/detail";
    }
}
