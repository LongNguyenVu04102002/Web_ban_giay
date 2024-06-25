package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller

public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/khachhang")
    public String listKhachHang(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "3") int size) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber()+1); // Vị trí trang hiện tại
        model.addAttribute("totalPages", khachHangPage.getTotalPages()); // Tổng số trang
        model.addAttribute("khachHang", new KhachHang()); // Thêm đối tượng khachHang vào model
        return "/left-menu"; // Trả về tên của view (khachhang.jsp)
    }


    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/khachhang";
    }


    @GetMapping("/detail/{id}")
    public String showKhachHangDetail(@PathVariable("id") Long id, Model model, @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "3") int size) {
        // Lấy thông tin khách hàng từ service
        // Lấy thông tin khách hàng từ service
        KhachHang khachHang = khachHangService.getKhachHangById(id);

        // Đưa thông tin khách hàng vào model để hiển thị trên form
        model.addAttribute("khachHang", khachHang);

        // Lấy danh sách khách hàng từ trang hiện tại
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        List<KhachHang> khachHangs = khachHangPage.getContent(); // Lấy danh sách khách hàng từ Page
        model.addAttribute("khachHangs", khachHangs); // Đưa danh sách khách hàng vào model

        // Thêm thông tin phân trang vào model để view JSP có thể sử dụng
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", khachHangPage.getTotalPages());
        model.addAttribute("size", size);

        return "/left-menu"; // Trả về trang chứa form (left-menu.jsp)
    }



    @PostMapping("/update/{id}")
    public String updateKhachHang(@PathVariable Long id,
                                  @ModelAttribute("khachHang") KhachHang khachHang,
                                  RedirectAttributes redirectAttributes) {
        KhachHang updatedKhachHang = khachHangService.updateKhachHang(khachHang, id);
        if (updatedKhachHang != null) {
            redirectAttributes.addFlashAttribute("message", "Đã cập nhật thông tin khách hàng thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cập nhật thông tin khách hàng thất bại.");
        }
        return "redirect:/khachhang";
    }


    @PostMapping("/saveKhachHang")
    public String saveKhachHang(@ModelAttribute("khachHang") @Valid KhachHang khachHang, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        int sdtLength = khachHang.getSdt().length();

        if (sdtLength < 10) {
            redirectAttributes.addFlashAttribute("errorMessage", "Số điện thoại phải có đủ 10 số");
        } else if (sdtLength > 10) {
            redirectAttributes.addFlashAttribute("errorMessage", "Số điện thoại chỉ được phép có đúng 10 số");
        } else {
            // Nếu không có lỗi, tiến hành lưu khách hàng
            khachHangService.saveKhachHang(khachHang);
            return "redirect:/khachhang";
        }

        return "redirect:/khachhang";
    }

    @PostMapping("/searchBySDT")
    public String searchCustomersBySDT(@RequestParam("sdt") String sdt,
                                       Model model, KhachHang khachHang) {
        model.addAttribute("khachHang", khachHang);
        // Implement logic to search customers by SDT
       List<KhachHang> searchResults = khachHangService.findBySdt(sdt);
        model.addAttribute("khachHangs", searchResults);

        return "left-menu"; // Replace with your JSP page name
    }

   
}
