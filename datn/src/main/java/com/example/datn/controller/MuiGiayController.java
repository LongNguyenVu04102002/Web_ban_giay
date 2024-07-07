package com.example.datn.controller;

import com.example.datn.entity.MauSac;
import com.example.datn.entity.MuiGiay;
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
public class MuiGiayController {

    @Autowired
    private IService<MuiGiay> muiGiayService;

    @GetMapping("/mui-giay")
    public String show(@ModelAttribute("muiGiay") MuiGiay muiGiay, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<MuiGiay> page = muiGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        muiGiay.setTrangThai(true);
        return "left-menu-mui-giay";
    }

    @PostMapping("/mui-giay/add")
    public String add(@Valid @ModelAttribute("muiGiay") MuiGiay muiGiay, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<MuiGiay> page = muiGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-mui-giay";
        }
        model.addAttribute("muiGiay", new MuiGiay());
        MuiGiay mg = MuiGiay.builder()
                .ten(muiGiay.getTen())
                .moTa(muiGiay.getMoTa())
                .trangThai(muiGiay.isTrangThai())
                .build();
        muiGiayService.addOrUpdate(mg);
        Page<MuiGiay> page = muiGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mui-giay";
    }

    @GetMapping("/mui-giay/detail/{id}")
    public String detail(@ModelAttribute("muiGiay") Optional<MuiGiay> muiGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("muiGiay", muiGiayService.getOne(id));
        Page<MuiGiay> page = muiGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-mui-giay";
    }

    @PostMapping("/mui-giay/update/{id}")
    public String update(@Valid @ModelAttribute("muiGiay") MuiGiay muiGiay, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<MuiGiay> page = muiGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-mui-giay";
        }
        model.addAttribute("muiGiay", new MuiGiay());
        muiGiayService.addOrUpdate(muiGiay);
        Page<MuiGiay> page = muiGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mui-giay";
    }

    @GetMapping("/mui-giay/remove/{id}")
    public String remove(@ModelAttribute("muiGiay") MuiGiay muiGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        muiGiayService.remove(id);
        Page<MuiGiay> page = muiGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/mui-giay";
    }
}
