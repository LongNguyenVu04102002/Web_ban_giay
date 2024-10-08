package com.example.datn.controller;

import com.example.datn.entity.DeGiay;
import com.example.datn.service.Impl.DeGiayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class DeGiayController {

    @Autowired
    private DeGiayServiceImpl deGiayService;

    @GetMapping("/degiay")
    public String show(Model model) {
        List<DeGiay> deGiayList = deGiayService.getAllDeGiay();
        DeGiay dg = new DeGiay();
        dg.setTrangThai(true);
        model.addAttribute("deGiayList", deGiayList);
        model.addAttribute("deGiay", dg);
        return "admin/includes/content/sanpham/degiay/home";
    }

    @PostMapping("/degiay/save")
    public String save(DeGiay deGiay, RedirectAttributes redirectAttributes) {
        if (deGiay.getDeGiayId() == null) {
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("update", true);
        }
        deGiayService.saveDeGiay(deGiay);
        return "redirect:/admin/sanpham/degiay";
    }

    @GetMapping("/degiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<DeGiay> deGiayList = deGiayService.getAllDeGiay();
        model.addAttribute("deGiayList", deGiayList);
        DeGiay deGiay = deGiayService.getById(id);
        model.addAttribute("deGiay", deGiay);
        return "admin/includes/content/sanpham/degiay/home";
    }

    @GetMapping("/degiay/delete/{id}")
    public String delete(@PathVariable Long id) {
        deGiayService.deleteDeGiay(id);
        return "redirect:/admin/sanpham/degiay";
    }

}
