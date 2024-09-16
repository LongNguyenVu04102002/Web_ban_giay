package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class KichThuocController {

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    @GetMapping("/kichthuoc")
    public String show(Model model) {
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        KichThuoc kt = new KichThuoc();
        kt.setTrangThai(true);
        model.addAttribute("kichThuocList", kichThuocList);
        model.addAttribute("kichThuoc", kt);
        return "admin/includes/content/sanpham/kichthuoc/home";
    }

    @PostMapping("/kichthuoc/save")
    public String save(KichThuoc kichThuoc) {
        kichThuocService.save(kichThuoc);
        return "redirect:/admin/sanpham/kichthuoc";
    }

    @GetMapping("/kichthuoc/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        model.addAttribute("kichThuocList", kichThuocList);
        KichThuoc kichThuoc = kichThuocService.getById(id);
        model.addAttribute("kichThuoc", kichThuoc);
        return "admin/includes/content/sanpham/kichthuoc/home";
    }

    @GetMapping("/kichthuoc/delete/{id}")
    public String delete(@PathVariable Long id) {
        kichThuocService.delete(id);
        return "redirect:/admin/sanpham/kichthuoc";
    }

}
