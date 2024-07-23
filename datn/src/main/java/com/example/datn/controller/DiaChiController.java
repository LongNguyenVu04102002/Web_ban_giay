package com.example.datn.controller;

import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/taikhoan")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("diaChi/delete/{id}")
    public String deleteDiaChi(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            diaChiService.deleteDiaChi(id);
            redirectAttributes.addFlashAttribute("message", "Xóa địa chỉ thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi xóa địa chỉ");
        }
        return "redirect:/admin/taikhoan/khachhang"; // Chuyển hướng về trang danh sách địa chỉ
    }
}
