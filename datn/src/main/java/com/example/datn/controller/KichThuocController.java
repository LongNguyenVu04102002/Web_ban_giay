package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class KichThuocController {

    @Autowired
    private IService<KichThuoc> kichThuocService;

    @GetMapping("/kich-thuoc")
    public String show(@ModelAttribute("kichThuoc") KichThuoc kichThuoc, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<KichThuoc> page = kichThuocService.pagination(p, 3);
        model.addAttribute("lst", page);
        kichThuoc.setTrangThai(true);
        return "kich-thuoc";
    }

    @PostMapping("/kich-thuoc/add")
    public String add(@Valid @ModelAttribute("kichThuoc") KichThuoc kichThuoc, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("kichThuoc", new KichThuoc());
        kichThuocService.addOrUpdate(kichThuoc);
        Page<KichThuoc> page = kichThuocService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/kich-thuoc";
    }

    @GetMapping("/kich-thuoc/detail/{id}")
    public String detail(@ModelAttribute("kichThuoc") Optional<KichThuoc> kichThuoc, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("kichThuoc", kichThuocService.getOne(id));
        Page<KichThuoc> page = kichThuocService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "kich-thuoc";
    }

    @PostMapping("/kich-thuoc/update/{id}")
    public String update(@Valid @ModelAttribute("kichThuoc") KichThuoc kichThuoc, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("kichThuoc", new KichThuoc());
        kichThuocService.addOrUpdate(kichThuoc);
        Page<KichThuoc> page = kichThuocService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/kich-thuoc";
    }

    @GetMapping("/kich-thuoc/remove/{id}")
    public String remove(@ModelAttribute("kichThuoc") KichThuoc kichThuoc, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        kichThuocService.remove(id);
        Page<KichThuoc> page = kichThuocService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/kich-thuoc";
    }
}
