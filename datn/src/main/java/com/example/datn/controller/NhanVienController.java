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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
//    @GetMapping("/searchNhanVien")
//    public String searchNhanVien(@RequestParam(name = "search", required = false) String searchName, Model model) {
//        List<NhanVien> nhanViens;
//        if (searchName != null && !searchName.isEmpty()) {
//            nhanViens = nhanVienService.findByHoTen(searchName);
//        } else {
//            return "redirect:/nhanvien";
//        }
//        model.addAttribute("nhanviens", nhanViens);
//        return "nhanvien/left-menu-nhan-vien";
//    }
@GetMapping("/searchNhanVien")
public String searchNhanVien(@RequestParam(name = "search", required = false) String search,
                             @RequestParam(name = "gender", required = false) Boolean gender,
                             Model model) {
    List<NhanVien> nhanViens = new ArrayList<>();
    if (search != null && !search.isEmpty() && gender != null) {
        nhanViens = nhanVienService.findByHoTenContainingAndGioiTinh(search, gender);
    } else if (search != null && !search.isEmpty()) {
        nhanViens = nhanVienService.findByHoTen(search);
    } else if (gender != null) {
        // Lọc theo giới tính khi không có tên được nhập
        // Điều này phụ thuộc vào yêu cầu cụ thể của bạn
        // Ví dụ: nếu gender = true, lấy nhân viên là nam và ngược lại
        // Thay đổi logic tại đây tùy theo yêu cầu của bạn
    } else {
        return "redirect:/nhanvien";
    }
    model.addAttribute("nhanviens",nhanViens);
    return "nhanvien/left-menu-nhan-vien";
}


@GetMapping("/addNhanVien")
public  String viewadd(Model model){
    model.addAttribute("nhanVien", new NhanVien());
        return "/nhanvien/viewadd";
}


    @PostMapping("/saveNhanVien") // add
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVien);
            return "nhanvien/viewadd";
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

        nhanVienService.saveNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }


    @GetMapping("/deleteNhanVien/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanvien";
    }
    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("nhanVienId") Long nhanVienId, @RequestParam("trangThai") boolean trangThai, RedirectAttributes redirectAttributes) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(nhanVienId).orElse(null);
        if (nhanVien != null) {
            nhanVien.setTrangThai(trangThai);
            nhanVienService.saveNhanVien(nhanVien);
            redirectAttributes.addFlashAttribute("message", "Cập nhật trạng thái thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy nhân viên!");
        }
        return "redirect:/nhanvien"; // Chuyển hướng về trang danh sách nhân viên
    }
}