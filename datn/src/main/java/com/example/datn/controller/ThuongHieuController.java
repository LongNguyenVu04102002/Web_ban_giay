package com.example.datn.controller;

import com.example.datn.entity.ThuongHieu;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class ThuongHieuController {

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @GetMapping("/thuonghieu")
    public String show(Model model) {
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "admin/includes/content/sanpham/thuonghieu/home";
    }

    @PostMapping("/thuonghieu/save")
    public String save(ThuongHieu thuongHieu) {
        thuongHieuService.save(thuongHieu);
        return "redirect:/admin/sanpham/thuonghieu";
    }

    @GetMapping("/thuonghieu/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        model.addAttribute("thuongHieuList", thuongHieuList);
        ThuongHieu thuongHieu = thuongHieuService.getById(id);
        model.addAttribute("thuongHieu", thuongHieu);
        return "admin/includes/content/sanpham/thuonghieu/home";
    }

    @GetMapping("/thuonghieu/delete/{id}")
    public String delete(@PathVariable Long id) {
        thuongHieuService.delete(id);
        return "redirect:/admin/sanpham/thuonghieu";
    }
    
}
