package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.DeGiay;
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
public class DeGiayController {

    @Autowired
    private IService<DeGiay> deGiayService;

    @GetMapping("/de-giay")
    public String show(@ModelAttribute("deGiay") DeGiay deGiay, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<DeGiay> page = deGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        deGiay.setTrangThai(true);
        return "left-menu-de-giay";
    }

    @PostMapping("/de-giay/add")
    public String add(@Valid @ModelAttribute("deGiay") DeGiay deGiay, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<DeGiay> page = deGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-de-giay";
        }
        model.addAttribute("deGiay", new DeGiay());
        DeGiay dg = DeGiay.builder()
                .ten(deGiay.getTen())
                .moTa(deGiay.getMoTa())
                .trangThai(deGiay.isTrangThai())
                .build();
        deGiayService.addOrUpdate(dg);
        Page<DeGiay> page = deGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/de-giay";
    }

    @GetMapping("/de-giay/detail/{id}")
    public String detail(@ModelAttribute("deGiay") Optional<DeGiay> deGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("deGiay", deGiayService.getOne(id));
        Page<DeGiay> page = deGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-de-giay";
    }

    @PostMapping("/de-giay/update/{id}")
    public String update(@Valid @ModelAttribute("deGiay") DeGiay deGiay, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<DeGiay> page = deGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-de-giay";
        }
        model.addAttribute("deGiay", new DeGiay());
        deGiayService.addOrUpdate(deGiay);
        Page<DeGiay> page = deGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/de-giay";
    }

    @GetMapping("/de-giay/remove/{id}")
    public String remove(@ModelAttribute("deGiay") DeGiay deGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        deGiayService.remove(id);
        Page<DeGiay> page = deGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/de-giay";
    }
}
