package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
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
        model.addAttribute("khachHang", khachHang);
        return "admin/includes/content/khachhang/form";
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

        // Lưu thông tin KhachHang
        khachHangService.save(khachHang);

        // Lấy thông tin địa chỉ từ KhachHang
        String thanhPho = khachHang.getDiaChiList().get(0).getThanhPho();
        String huyen = khachHang.getDiaChiList().get(0).getHuyen();
        String xa = khachHang.getDiaChiList().get(0).getXa();

        // Tạo đối tượng DiaChi mới và thiết lập quan hệ với KhachHang
        DiaChi diaChi = new DiaChi();
        diaChi.setThanhPho(thanhPho);
        diaChi.setHuyen(huyen);
        diaChi.setXa(xa);
        diaChi.setKhachHang(khachHang); // Thiết lập quan hệ với KhachHang

        // Lưu DiaChi vào cơ sở dữ liệu thông qua service
        diaChiService.saveDiaChi(diaChi);

        return "redirect:/admin/taikhoan/khachhang";
    }


    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/admin/taikhoan/khachhang";
    }

    @GetMapping("/khachhang/search")
    public String searchKhachHang(@RequestParam(value = "sdt", required = false) String sdt,
                                  @RequestParam(value = "hoTen", required = false) String hoTen,
                                  @RequestParam(value = "email", required = false) String email,
                                  Model model) {
        List<KhachHang> result = khachHangService.searchKhachHang(sdt, hoTen, email);
        model.addAttribute("khachHangList", result);
        return "admin/includes/content/khachhang/home";
    }

}
