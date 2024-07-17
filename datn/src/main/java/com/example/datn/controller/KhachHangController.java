package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.DiaChiService;
import com.example.datn.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/taikhoan")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
private DiaChiService diaChiService;

    @GetMapping("/khachhang")
    public String show(Model model) {
        List<KhachHang> khachHangList = khachHangService.getAll();
        model.addAttribute("khachHangList", khachHangList);
        return "admin/includes/content/khachhang/home";

    }

    @GetMapping("/khachhang/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        KhachHang khachHang = khachHangService.getById(id);
        List<DiaChi> diaChiList = diaChiService.findByKhachHangId(id);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("diaChiList", diaChiList);
        return "admin/includes/content/khachhang/detail";
    }


    @GetMapping("/khachhang/form")
    public String viewAdd(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "admin/includes/content/khachhang/form";
    }

    @PostMapping("/khachhang/save")
    public String save(@Valid KhachHang khachHang, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/form";
        }

        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            diaChi.setKhachHang(khachHang);
        }
        khachHangService.save(khachHang);
        return "redirect:/admin/taikhoan/khachhang";
    }

    @GetMapping("/{khachHangId}/diachi/changestatus/{diaChiId}")
    public String changeDiaChiStatus(@PathVariable("khachHangId") Long khachHangId,
                                     @PathVariable("diaChiId") Long diaChiId,
                                     @RequestParam("newStatus") boolean newStatus) {
        khachHangService.changeDiaChiStatus(diaChiId, newStatus);
        return "redirect:/admin/khachhang/" + khachHangId + "/detail"; // Redirect về trang chi tiết khách hàng
    }


    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/admin/taikhoan/khachhang";
    }

}
