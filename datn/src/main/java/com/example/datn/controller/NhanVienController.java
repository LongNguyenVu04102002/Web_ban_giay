package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/nhanvien")
    public String listNhanVien(Model model,
                               NhanVien nhanVien,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "5") int size
                              ) {

        Page<NhanVien> nhanVienPage = nhanVienService.getAllNhanVienByPage(page, size);

        model.addAttribute("nhanviens", nhanVienPage.getContent());
        model.addAttribute("currentPage", nhanVienPage.getNumber() + 1); // Vị trí trang hiện tại
        model.addAttribute("totalPages", nhanVienPage.getTotalPages()); // Tổng số trang
        model.addAttribute("nhanVien", new NhanVien()); // Thêm đối tượng khachHang vào model

        nhanVien.setTrangThai(true);

        return "/nhanvien/left-menu-nhan-vien";

    }
    @GetMapping("/searchNhanVien")
    public String searchNhanVien(@RequestParam(name = "search", required = false) String searchName, Model model) {
        List<NhanVien> nhanViens;
        if (searchName != null && !searchName.isEmpty()) {
            nhanViens = nhanVienService.findByHoTen(searchName);
        } else {
            nhanViens = nhanVienService.getAllNhanhVien();
        }
        model.addAttribute("nhanviens", nhanViens);
        return "nhanvien/left-menu-nhan-vien";
    }


    @PostMapping("/saveNhanVien") // add
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVien);
            return "nhanvien/left-menu-nhan-vien";
        }
        nhanVien.setTrangThai(true);
        nhanVienService.saveNhanVien(nhanVien);

        return "redirect:/nhanvien";
    }

    @GetMapping("/nhanvien/{nhanVienId}/toggle")
    public String toggleTrangThai(@PathVariable Long nhanVienId) {
        nhanVienService.toggleTrangThai(nhanVienId);
        return "redirect:/nhanvien";
    }

    @GetMapping("/editNhanVien/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<NhanVien> nhanVien = nhanVienService.getNhanVienById(id);
        if (nhanVien.isPresent()) {
            model.addAttribute("nhanVien", nhanVien.get());
            return "/nhanvien/updatenv";
        } else {
            return "redirect:/nhanvien";
        }
    }

    @PostMapping("/editNhanVien/{id}")
    public String updateNhanVien(@PathVariable("id") long id, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            nhanVien.setNhanVienId(id);
            return "nhanvien/updatenv";
        }

        // Set ID to ensure the entity is updated instead of created as new
        nhanVien.setNhanVienId(id);
        nhanVien.setTrangThai(true);
        nhanVienService.saveNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }


    @GetMapping("/deleteNhanVien/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanvien";
    }


}