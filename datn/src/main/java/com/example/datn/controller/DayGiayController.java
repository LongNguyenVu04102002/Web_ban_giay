package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.DayGiay;
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
public class DayGiayController {

    @Autowired
    private IService<DayGiay> dayGiayService;

    @GetMapping("/day-giay")
    public String show(@ModelAttribute("dayGiay") DayGiay dayGiay, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<DayGiay> page = dayGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        dayGiay.setTrangThai(true);
        return "left-menu-day-giay";
    }

    @PostMapping("/day-giay/add")
    public String add(@Valid @ModelAttribute("dayGiay") DayGiay dayGiay, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<DayGiay> page = dayGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-day-giay";
        }
        model.addAttribute("dayGiay", new DayGiay());
        DayGiay dg = DayGiay.builder()
                .ten(dayGiay.getTen())
                .moTa(dayGiay.getMoTa())
                .trangThai(dayGiay.isTrangThai())
                .build();
        dayGiayService.addOrUpdate(dg);
        Page<DayGiay> page = dayGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/day-giay";
    }

    @GetMapping("/day-giay/detail/{id}")
    public String detail(@ModelAttribute("dayGiay") Optional<DayGiay> dayGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("dayGiay", dayGiayService.getOne(id));
        Page<DayGiay> page = dayGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-day-giay";
    }

    @PostMapping("/day-giay/update/{id}")
    public String update(@Valid @ModelAttribute("dayGiay") DayGiay dayGiay, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<DayGiay> page = dayGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-day-giay";
        }
        model.addAttribute("dayGiay", new DayGiay());
        dayGiayService.addOrUpdate(dayGiay);
        Page<DayGiay> page = dayGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/day-giay";
    }

    @GetMapping("/day-giay/remove/{id}")
    public String remove(@ModelAttribute("dayGiay") DayGiay dayGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        dayGiayService.remove(id);
        Page<DayGiay> page = dayGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/day-giay";
    }
}
