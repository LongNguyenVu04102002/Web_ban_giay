package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.CoGiay;
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
public class CoGiayController {

    @Autowired
    private IService<CoGiay> coGiayService;

    @GetMapping("/co-giay")
    public String show(@ModelAttribute("coGiay") CoGiay coGiay, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<CoGiay> page = coGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        coGiay.setTrangThai(true);
        return "left-menu-co-giay";
    }

    @PostMapping("/co-giay/add")
    public String add(@Valid @ModelAttribute("coGiay") CoGiay coGiay, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<CoGiay> page = coGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-co-giay";
        }
        model.addAttribute("coGiay", new CoGiay());
        CoGiay cg = CoGiay.builder()
                .ten(coGiay.getTen())
                .moTa(coGiay.getTen())
                .trangThai(coGiay.isTrangThai())
                .build();
        coGiayService.addOrUpdate(cg);
        Page<CoGiay> page = coGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/co-giay";
    }

    @GetMapping("/co-giay/detail/{id}")
    public String detail(@ModelAttribute("coGiay") Optional<CoGiay> coGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("coGiay", coGiayService.getOne(id));
        Page<CoGiay> page = coGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-co-giay";
    }

    @PostMapping("/co-giay/update/{id}")
    public String update(@Valid @ModelAttribute("coGiay") CoGiay coGiay, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<CoGiay> page = coGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-co-giay";
        }
        model.addAttribute("coGiay", new CoGiay());
        coGiayService.addOrUpdate(coGiay);
        Page<CoGiay> page = coGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/co-giay";
    }

    @GetMapping("/co-giay/remove/{id}")
    public String remove(@ModelAttribute("coGiay") CoGiay coGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        coGiayService.remove(id);
        Page<CoGiay> page = coGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/co-giay";
    }
}
