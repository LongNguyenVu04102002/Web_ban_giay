package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.LotGiay;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LotGiayController {

    @Autowired
    private IService<LotGiay> lotGiayService;

    @GetMapping("/lot-giay")
    public String show(@ModelAttribute("lotGiay") LotGiay lotGiay, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<LotGiay> page = lotGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        lotGiay.setTrangThai(true);
        return "left-menu-lot-giay";
    }

    @PostMapping("/lot-giay/add")
    public String add(@Valid @ModelAttribute("lotGiay") LotGiay lotGiay, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<LotGiay> page = lotGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-lot-giay";
        }
        model.addAttribute("lotGiay", new LotGiay());
        LotGiay lg = LotGiay.builder()
                .ten(lotGiay.getTen())
                .moTa(lotGiay.getMoTa())
                .trangThai(lotGiay.isTrangThai())
                .build();
        lotGiayService.addOrUpdate(lg);
        Page<LotGiay> page = lotGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/lot-giay";
    }

    @GetMapping("/lot-giay/detail/{id}")
    public String detail(@ModelAttribute("lotGiay") Optional<LotGiay> lotGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("lotGiay", lotGiayService.getOne(id));
        Page<LotGiay> page = lotGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-lot-giay";
    }

    @PostMapping("/lot-giay/update/{id}")
    public String update(@Valid @ModelAttribute("lotGiay") LotGiay lotGiay, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<LotGiay> page = lotGiayService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-lot-giay";
        }
        model.addAttribute("lotGiay", new LotGiay());
        lotGiayService.addOrUpdate(lotGiay);
        Page<LotGiay> page = lotGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/lot-giay";
    }

    @GetMapping("/lot-giay/remove/{id}")
    public String remove(@ModelAttribute("lotGiay") LotGiay lotGiay, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        lotGiayService.remove(id);
        Page<LotGiay> page = lotGiayService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/lot-giay";
    }
}
