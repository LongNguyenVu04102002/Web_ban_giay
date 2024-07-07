package com.example.datn.controller;

import com.example.datn.entity.MauSac;
import com.example.datn.entity.ThuongHieu;
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
public class ThuongHieuController {

    @Autowired
    private IService<ThuongHieu> thuongHieuService;

    @GetMapping("/thuong-hieu")
    public String show(@ModelAttribute("thuongHieu") ThuongHieu thuongHieu, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        thuongHieu.setTrangThai(true);
        return "left-menu-thuong-hieu";
    }

    @PostMapping("/thuong-hieu/add")
    public String add(@Valid @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-thuong-hieu";
        }
        model.addAttribute("thuongHieu", new ThuongHieu());
        thuongHieuService.addOrUpdate(thuongHieu);
        Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/thuong-hieu";
    }

    @GetMapping("/thuong-hieu/detail/{id}")
    public String detail(@ModelAttribute("thuongHieu") Optional<ThuongHieu> thuongHieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("thuongHieu", thuongHieuService.getOne(id));
        Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-thuong-hieu";
    }

    @PostMapping("/thuong-hieu/update/{id}")
    public String update(@Valid @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-thuong-hieu";
        }
        model.addAttribute("thuongHieu", new ThuongHieu());
        thuongHieuService.addOrUpdate(thuongHieu);
        Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/thuong-hieu";
    }

    @GetMapping("/thuong-hieu/remove/{id}")
    public String remove(@ModelAttribute("thuongHieu") ThuongHieu thuongHieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        thuongHieuService.remove(id);
        Page<ThuongHieu> page = thuongHieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/thuong-hieu";
    }
}
