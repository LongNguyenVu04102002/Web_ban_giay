package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.service.Impl.PhieuGiamGiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaServiceImpl phieuGiamGiaService;

    @GetMapping("/giamgia")
    public String getAll(Model model) {
        List<PhieuGiamGia> phieuGiamGiaList = phieuGiamGiaService.getAll();
        model.addAttribute("phieuGiamGiaList", phieuGiamGiaList);
        return "admin/includes/content/giamgia/home";
    }

    @GetMapping("/giamgia/form")
    public String viewForm(Model model) {
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        return "admin/includes/content/giamgia/form";
    }

    @GetMapping("/giamgia/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaService.getById(id);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        return "admin/includes/content/giamgia/form";
    }

    @PostMapping("/giamgia/save")
    public String save(PhieuGiamGia phieuGiamGia) {
        phieuGiamGiaService.save(phieuGiamGia);
        return "redirect:/admin/giamgia";
    }


    @PostMapping("/giamgia/update/{id}")
    public String update(@PathVariable Long id) {
        phieuGiamGiaService.update(id);
        return "redirect:/admin/giamgia";
    }

}
