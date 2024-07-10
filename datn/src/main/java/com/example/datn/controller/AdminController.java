package com.example.datn.controller;

import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.service.Impl.HoaDonChiTietServiceImpl;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private HoaDonChiTietServiceImpl hoaDonChiTietService;

    @GetMapping("/admin/thongke")
    public String thongKe(){
        return "admin/includes/content/thongke/thongke";
    }

    @GetMapping("/admin/banhang")
    public String banHang(){
        return "admin/includes/content/banhang/banhang";
    }

    @GetMapping("/hoadon/hoadonchitiet")
    public List<HoaDonChiTiet> hoaDonChiTiet(){
        return hoaDonChiTietService.getAll();
    }

}