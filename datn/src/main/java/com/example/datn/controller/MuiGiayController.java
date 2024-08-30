package com.example.datn.controller;

import com.example.datn.entity.MuiGiay;
import com.example.datn.service.Impl.MuiGiayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class MuiGiayController {
    @Autowired
    private MuiGiayServiceImpl muiGiayService;

    @GetMapping("/muigiay")
    public String show(Model model) {
        List<MuiGiay> muiGiayList = muiGiayService.getAllMuiGiay();
        model.addAttribute("muiGiayList", muiGiayList);
        model.addAttribute("muiGiay", new MuiGiay());
        return "admin/includes/content/sanpham/muigiay/home";
    }

    @PostMapping("/muigiay/save")
    public String save(MuiGiay muiGiay) {
        muiGiay.setTen(muiGiay.getTen().trim());
        muiGiayService.saveMuiGiay(muiGiay);
        return "redirect:/admin/sanpham/muigiay";
    }

    @GetMapping("/muigiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<MuiGiay> muiGiayList = muiGiayService.getAllMuiGiay();
        model.addAttribute("muiGiayList", muiGiayList);
        MuiGiay muiGiay = muiGiayService.getById(id);
        model.addAttribute("muiGiay", muiGiay);
        return "admin/includes/content/sanpham/muigiay/home";
    }

    @GetMapping("/muigiay/delete/{id}")
    public String delete(@PathVariable Long id) {
        muiGiayService.deleteMuiGiay(id);
        return "redirect:/admin/sanpham/muigiay";
    }

}
