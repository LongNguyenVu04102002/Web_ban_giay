package com.example.datn.controller;

import com.example.datn.entity.MauSac;
import com.example.datn.service.Impl.MauSacServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class MauSacController {
    @Autowired
    private MauSacServiceImpl mauSacService;

    @GetMapping("/mausac")
    public String show(Model model) {
        List<MauSac> mauSacList = mauSacService.getAll();
        MauSac ms = new MauSac();
        ms.setTrangThai(true);
        model.addAttribute("mauSacList", mauSacList);
        model.addAttribute("mauSac", ms);
        return "admin/includes/content/sanpham/mausac/home";
    }

    @PostMapping("/mausac/save")
    public String save(MauSac mauSac, RedirectAttributes redirectAttributes) {
        if (mauSac.getMauSacId() == null) {
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("update", true);
        }
        mauSacService.save(mauSac);
        return "redirect:/admin/sanpham/mausac";
    }

    @GetMapping("/mausac/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<MauSac> mauSacList = mauSacService.getAll();
        model.addAttribute("mauSacList", mauSacList);
        MauSac mauSac = mauSacService.getById(id);
        model.addAttribute("mauSac", mauSac);
        return "admin/includes/content/sanpham/mausac/home";
    }

    @GetMapping("/mausac/delete/{id}")
    public String delete(@PathVariable Long id) {
        mauSacService.delete(id);
        return "redirect:/admin/sanpham/mausac";
    }

}
