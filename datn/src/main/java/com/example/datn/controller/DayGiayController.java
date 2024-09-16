package com.example.datn.controller;

import com.example.datn.entity.DayGiay;
import com.example.datn.service.Impl.DayGiayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class DayGiayController {

    @Autowired
    private DayGiayServiceImpl dayGiayService;

    @GetMapping("/daygiay")
    public String show(Model model) {
        List<DayGiay> dayGiayList = dayGiayService.getAllDayGiay();
        DayGiay dg = new DayGiay();
        dg.setTrangThai(true);
        model.addAttribute("dayGiayList", dayGiayList);
        model.addAttribute("dayGiay", dg);
        return "admin/includes/content/sanpham/daygiay/home";
    }

    @PostMapping("/daygiay/save")
    public String save(DayGiay dayGiay) {
        dayGiayService.saveDayGiay(dayGiay);
        return "redirect:/admin/sanpham/daygiay";
    }

    @GetMapping("/daygiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<DayGiay> dayGiayList = dayGiayService.getAllDayGiay();
        model.addAttribute("dayGiayList", dayGiayList);
        DayGiay dayGiay = dayGiayService.getById(id);
        model.addAttribute("dayGiay", dayGiay);
        return "admin/includes/content/sanpham/daygiay/home";
    }

    @GetMapping("/daygiay/delete/{id}")
    public String delete(@PathVariable Long id) {
        dayGiayService.deleteDayGiay(id);
        return "redirect:/admin/sanpham/daygiay";
    }

}
