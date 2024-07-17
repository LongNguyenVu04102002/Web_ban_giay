package com.example.datn.controller;

import com.example.datn.entity.LotGiay;
import com.example.datn.service.impl.LotGiayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class LotGiayController {

    @Autowired
    private LotGiayServiceImpl lotGiayService;

    @GetMapping("/lotgiay")
    public String show(Model model) {
        List<LotGiay> lotGiayList = lotGiayService.getAllLotGiay();
        model.addAttribute("lotGiayList", lotGiayList);
        model.addAttribute("lotGiay", new LotGiay());
        return "admin/includes/content/sanpham/lotgiay/home";
    }

    @PostMapping("/lotgiay/save")
    public String save(LotGiay lotGiay) {
        lotGiayService.saveLotGiay(lotGiay);
        return "redirect:/admin/sanpham/lotgiay";
    }

    @GetMapping("/lotgiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<LotGiay> lotGiayList = lotGiayService.getAllLotGiay();
        model.addAttribute("lotGiayList", lotGiayList);
        LotGiay lotGiay = lotGiayService.getById(id);
        model.addAttribute("lotGiay", lotGiay);
        return "admin/includes/content/sanpham/lotgiay/home";
    }

    @GetMapping("/lotgiay/delete/{id}")
    public String delete(@PathVariable Long id) {
        lotGiayService.deleteLotGiay(id);
        return "redirect:/admin/sanpham/lotgiay";
    }
    
}
