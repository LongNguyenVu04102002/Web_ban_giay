package com.example.datn.controller;

import com.example.datn.entity.CoGiay;
import com.example.datn.service.Impl.CoGiayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class CoGiayController {

    @Autowired
    private CoGiayServiceImpl coGiayService;

    @GetMapping("/cogiay")
    public String show(Model model) {
        List<CoGiay> coGiayList = coGiayService.getAllCoGiay();
        model.addAttribute("coGiayList", coGiayList);
        model.addAttribute("coGiay", new CoGiay());
        return "admin/includes/content/sanpham/cogiay/home";
    }

    @PostMapping("/cogiay/save")
    public String add(CoGiay coGiay) {
        coGiayService.saveCoGiay(coGiay);
        return "redirect:/admin/sanpham/cogiay";
    }

    @GetMapping("/cogiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<CoGiay> coGiayList = coGiayService.getAllCoGiay();
        model.addAttribute("coGiayList", coGiayList);
        CoGiay coGiay = coGiayService.getById(id);
        model.addAttribute("coGiay", coGiay);
        return "admin/includes/content/sanpham/cogiay/home";
    }

    @GetMapping("/cogiay/delete/{id}")
    public String delete(@PathVariable Long id) {
        coGiayService.deleteCoGiay(id);
        return "redirect:/admin/sanpham/cogiay";
    }

}
