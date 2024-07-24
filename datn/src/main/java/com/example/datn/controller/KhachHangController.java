package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/taikhoan")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;



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
        return "admin/includes/content/khachhang/detail";
    }

    @GetMapping("/khachhang/update/{id}")
    public String updateView(@PathVariable Long id, Model model) {
        KhachHang khachHang = khachHangService.getById(id);
        model.addAttribute("khachHang", khachHang);
        return "admin/includes/content/khachhang/update";
    }

    @GetMapping("/khachhang/form")
    public String viewAdd(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "admin/includes/content/khachhang/form";
    }

    @PostMapping("/khachhang/save")
    public String save(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Kiểm tra trùng lặp số điện thoại
        if (khachHangService.isSdtExist(khachHang.getSdt())) {
            result.addError(new FieldError("khachHang", "sdt", "Số điện thoại đã tồn tại"));
        }

        // Kiểm tra trùng lặp email
        if (khachHangService.isEmailExist(khachHang.getEmail())) {
            result.addError(new FieldError("khachHang", "email", "Email đã tồn tại"));
        }

        // Nếu có lỗi, trả lại trang form với các lỗi đã thêm
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/form";
        }

        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            diaChi.setKhachHang(khachHang);
        }
        khachHangService.save(khachHang);
        redirectAttributes.addFlashAttribute("message", "Thêm khách hàng thành công!");
        return "redirect:/admin/taikhoan/khachhang";
    }



    @PostMapping("/khachhang/update")
    public String update(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        // Kiểm tra trùng lặp số điện thoại
        if (khachHangService.isPhoneNumberDuplicate(khachHang.getSdt(), khachHang.getKhachHangId())) {
            result.rejectValue("sdt", "error.khachHang", "Số điện thoại đã tồn tại.");
        }

        // Kiểm tra trùng lặp email
        if (khachHangService.isEmailDuplicate(khachHang.getEmail(), khachHang.getKhachHangId())) {
            result.rejectValue("email", "error.khachHang", "Email đã tồn tại.");
        }

        // Nếu có lỗi, trả lại trang form với các lỗi đã thêm
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/update";
        }

        // Cập nhật thông tin khách hàng
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thông tin khách hàng thành công!");
        khachHangService.update(khachHang);

        return "redirect:/admin/taikhoan/khachhang/detail/" + khachHang.getKhachHangId();
    }


    @PostMapping("/khachhang/updateDiaChi")
    public String updateDiaChi(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        // Kiểm tra trùng lặp số điện thoại
        if (khachHangService.isPhoneNumberDuplicate(khachHang.getSdt(), khachHang.getKhachHangId())) {
            result.rejectValue("sdt", "error.khachHang", "Số điện thoại đã tồn tại.");
        }

        // Kiểm tra trùng lặp email
        if (khachHangService.isEmailDuplicate(khachHang.getEmail(), khachHang.getKhachHangId())) {
            result.rejectValue("email", "error.khachHang", "Email đã tồn tại.");
        }

        // Nếu có lỗi, trả lại trang form với các lỗi đã thêm
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/update";
        }

        // Loại bỏ các địa chỉ không hợp lệ
        List<DiaChi> validDiaChiList = new ArrayList<>();
        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            if (diaChi.getThanhPho() != null && !diaChi.getThanhPho().isEmpty() &&
                    diaChi.getHuyen() != null && !diaChi.getHuyen().isEmpty() &&
                    diaChi.getXa() != null && !diaChi.getXa().isEmpty() &&
                    diaChi.getDiaChi() != null && !diaChi.getDiaChi().isEmpty()) {
                diaChi.setKhachHang(khachHang);  // Gán khách hàng cho địa chỉ hợp lệ
                validDiaChiList.add(diaChi);  // Thêm địa chỉ hợp lệ vào danh sách
            }
        }

        // Cập nhật danh sách địa chỉ của khách hàng
        khachHang.setDiaChiList(validDiaChiList);

        // Cập nhật thông tin khách hàng
        redirectAttributes.addFlashAttribute("message", "Thêm địa chỉ thành công!");
        khachHangService.update(khachHang);

        return "redirect:/admin/taikhoan/khachhang/detail/" + khachHang.getKhachHangId();
    }




    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId, RedirectAttributes redirectAttributes) {
        KhachHang khachHang = khachHangService.toggleTrangThai(khachHangId);
        boolean isActive = khachHang.isTrangThai();
        String newStatusText = isActive ? "ngừng hoạt động" : "hoạt động";
        String message = "Trạng thái của khách hàng có số điện thoại " + khachHang.getSdt() + " đã được thay đổi thành " + newStatusText + ".";
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isActive", isActive);
        return "redirect:/admin/taikhoan/khachhang";
    }





}
