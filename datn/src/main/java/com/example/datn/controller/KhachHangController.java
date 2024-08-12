package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.Impl.EmailService;
import com.example.datn.service.KhachHangService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private EmailService emailService;

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
    public String save(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws MessagingException {
        // Kiểm tra trùng lặp số điện thoại
        if (khachHangService.isSdtExist(khachHang.getSdt())) {
            result.addError(new FieldError("khachHang", "sdt", "Số điện thoại đã tồn tại"));
        }
        if (khachHangService.isEmailExist(khachHang.getEmail())) {
            result.addError(new FieldError("khachHang", "email", "Email đã tồn tại"));
        }
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/form";
        }
        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            diaChi.setKhachHang(khachHang);
        }
        khachHangService.save(khachHang);
        emailService.sendEmail(khachHang.getEmail(),"You have successfully registered an account.",
                "Your password is: " + khachHang.getSdt());
        redirectAttributes.addFlashAttribute("message", "Thêm khách hàng thành công!");
        return "redirect:/admin/taikhoan/khachhang";
    }


    @PostMapping("/khachhang/update")
    public String update(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model) {
        if (khachHangService.isPhoneNumberDuplicate(khachHang.getSdt(), khachHang.getKhachHangId())) {
            result.rejectValue("sdt", "error.khachHang", "Số điện thoại đã tồn tại.");
        }
        if (khachHangService.isEmailDuplicate(khachHang.getEmail(), khachHang.getKhachHangId())) {
            result.rejectValue("email", "error.khachHang", "Email đã tồn tại.");
        }
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/update";
        }
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
        khachHang.setDiaChiList(validDiaChiList);
        khachHangService.update(khachHang);

        return "redirect:/admin/taikhoan/khachhang/detail/" + khachHang.getKhachHangId();
    }



    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/admin/taikhoan/khachhang";
    }

}
