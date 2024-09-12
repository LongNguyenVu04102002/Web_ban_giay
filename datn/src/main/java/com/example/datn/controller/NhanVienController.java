package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.Impl.EmailService;
import com.example.datn.service.Impl.NhanVienServiceImpl;
import jakarta.mail.MessagingException;
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

    @Autowired
    private EmailService emailService;

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
    public String save(@Valid NhanVien nhanVien, BindingResult bindingResult, Model model) throws MessagingException {
        // Kiểm tra xem là thêm mới hay cập nhật
        boolean isUpdate = nhanVien.getNhanVienId() != null; // Giả sử id là khoá chính

        // Kiểm tra email đã tồn tại, trừ khi đang trong thao tác cập nhật
        NhanVien existingNhanVien = nhanVienService.existsByEmail(nhanVien.getEmail());
        if (!isUpdate && existingNhanVien != null) {
            bindingResult.rejectValue("email", "error.nhanVien", "Email đã tồn tại");
            return "admin/includes/content/nhanvien/form";
        }

        // Kiểm tra số điện thoại đã tồn tại, trừ khi đang trong thao tác cập nhật
        if (!isUpdate && nhanVienService.existsBySdt(nhanVien.getSdt())) {
            bindingResult.rejectValue("sdt", "error.nhanVien", "Số điện thoại đã tồn tại");
            return "admin/includes/content/nhanvien/form";
        }

        // Nếu có lỗi ràng buộc, trả về form để sửa
        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVien);
            return "admin/includes/content/nhanvien/form";
        }

        // Nếu không có lỗi, lưu hoặc cập nhật nhân viên
        nhanVienService.save(nhanVien);

        // Nếu là thêm mới, gửi email thông báo
        if (!isUpdate) {
            emailService.sendEmail(nhanVien.getEmail(), "Bạn đã đăng ký tài khoản thành công.",
                    "Mật khẩu của bạn là: " + nhanVien.getSdt());
        }

        return "redirect:/admin/taikhoan/nhanvien";
    }


    @GetMapping("/nhanvien/{nhanVienId}/toggle")
    public String toggleTrangThai(@PathVariable Long nhanVienId) {
        nhanVienService.toggleTrangThai(nhanVienId);
        return "redirect:/admin/taikhoan/nhanvien";
    }


}