package com.example.datn.controller;

import com.example.datn.entity.HoaDon;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @GetMapping("/admin/thongke")
    public String thongKe(){
        return "admin/includes/content/thongke/thongke";
    }

    @GetMapping("/admin/banhang")
    public String banHang(){
        return "admin/includes/content/banhang/banhang";
    }

    @GetMapping("/admin/hoadon")
    public String hoaDon(Model model){
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();
        model.addAttribute("hoaDonList", hoaDonList);
        return "admin/includes/content/hoadon/hoadon";
    }



}
