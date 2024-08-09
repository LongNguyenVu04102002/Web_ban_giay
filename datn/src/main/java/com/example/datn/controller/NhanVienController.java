package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.Impl.NhanVienServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/taikhoan")
public class NhanVienController {

    @Autowired
    NhanVienServiceImpl nhanVienService;

    @GetMapping("/nhanvien")
    public String show(Model model) {
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanVienList", nhanVienList);
        return "admin/includes/content/nhanvien/home";

    }

    @GetMapping("/nhanvien/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "admin/includes/content/nhanvien/form";
    }

    @GetMapping("/nhanvien/form")
    public String viewAdd(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "admin/includes/content/nhanvien/form";
    }
    @PostMapping("/nhanvien/save")
    public String save(@Valid NhanVien nhanVien, BindingResult bindingResult, Model model) {
        boolean isNewEmployee = (nhanVien.getNhanVienId() == null);

        if (isNewEmployee) {
            // Kiểm tra trùng email và số điện thoại chỉ khi thêm mới nhân viên
            if (nhanVienService.existsByEmail(nhanVien.getEmail())) {
                bindingResult.rejectValue("email", "error.nhanVien", "Email đã tồn tại");
            }
            if (nhanVienService.existsBySdt(nhanVien.getSdt())) {
                bindingResult.rejectValue("sdt", "error.nhanVien", "Số điện thoại đã tồn tại");
            }
        } else {
            // Khi cập nhật, chỉ kiểm tra trùng email và số điện thoại nếu chúng đã thay đổi
            NhanVien existingNhanVien = nhanVienService.getById(nhanVien.getNhanVienId());
            if (!nhanVien.getEmail().equals(existingNhanVien.getEmail()) && nhanVienService.existsByEmail(nhanVien.getEmail())) {
                bindingResult.rejectValue("email", "error.nhanVien", "Email đã tồn tại");
            }
            if (!nhanVien.getSdt().equals(existingNhanVien.getSdt()) && nhanVienService.existsBySdt(nhanVien.getSdt())) {
                bindingResult.rejectValue("sdt", "error.nhanVien", "Số điện thoại đã tồn tại");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVien);
            return "admin/includes/content/nhanvien/form";
        } else {
            nhanVienService.save(nhanVien);
            return "redirect:/admin/taikhoan/nhanvien";
        }
    }
    @GetMapping("/nhanvien/{nhanVienId}/toggle")
    public String toggleTrangThai(@PathVariable Long nhanVienId) {
        nhanVienService.toggleTrangThai(nhanVienId);
        return "redirect:/admin/taikhoan/nhanvien";
    }


}