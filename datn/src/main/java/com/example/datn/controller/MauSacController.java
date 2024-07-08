package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MauSacController {

    @Autowired
    private IService<MauSac> mauSacService;

    @GetMapping("/mau-sac")
    public String show(@ModelAttribute("mauSac") MauSac mauSac, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<MauSac> page = mauSacService.pagination(p, 10);
        model.addAttribute("lst", page);
        mauSac.setTrangThai(true);
        return "left-menu-mau-sac";
    }

    @PostMapping("/mau-sac/add")
    public String add(@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<MauSac> page = mauSacService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-mau-sac";
        }
        model.addAttribute("mauSac", new MauSac());
        MauSac ms = MauSac.builder()
                .ten(mauSac.getTen())
                .moTa(mauSac.getMoTa())
                .trangThai(mauSac.isTrangThai())
                .build();
        mauSacService.addOrUpdate(ms);
        Page<MauSac> page = mauSacService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/detail/{id}")
    public String detail(@ModelAttribute("mauSac") Optional<MauSac> mauSac, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("mauSac", mauSacService.getOne(id));
        Page<MauSac> page = mauSacService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-mau-sac";
    }

    @PostMapping("/mau-sac/update/{id}")
    public String update(@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<MauSac> page = mauSacService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-mau-sac";
        }
        model.addAttribute("mauSac", new MauSac());
        mauSacService.addOrUpdate(mauSac);
        Page<MauSac> page = mauSacService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/remove/{id}")
    public String remove(@ModelAttribute("mauSac") MauSac mauSac, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        mauSacService.remove(id);
        Page<MauSac> page = mauSacService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }
}
