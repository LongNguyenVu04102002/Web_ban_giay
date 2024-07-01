package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.service.DiaChiService;
import com.example.datn.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller

public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/khachhang")
    public String listKhachHang(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "8") int size) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber()+1); // Vị trí trang hiện tại
        model.addAttribute("totalPages", khachHangPage.getTotalPages()); // Tổng số trang
        model.addAttribute("khachHang", new KhachHang()); // Thêm đối tượng khachHang vào model
        return "khachHang/left-menu-khachhang"; // Trả về tên của view (khachhang.jsp)
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


//
//    @PostMapping("/update/{id}")
//    public String updateKhachHang(@PathVariable Long id,
//                                  @ModelAttribute("khachHang") KhachHang khachHang,
//                                  RedirectAttributes redirectAttributes) {
//        KhachHang updatedKhachHang = khachHangService.updateKhachHang(khachHang, id);
//        if (updatedKhachHang != null) {
//            redirectAttributes.addFlashAttribute("message", "Đã cập nhật thông tin khách hàng thành công.");
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Cập nhật thông tin khách hàng thất bại.");
//        }
//        return "redirect:/khachhang";
//    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khachHang/left-menu-addKhachHang";
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

        List<KhachHang> searchResults = khachHangService.findBySdt(sdt);
        model.addAttribute("khachHangs", searchResults);

        return "khachHang/left-menu-khachhang";
    }


    @GetMapping("/filterByGender")
    public String filterByGender(@RequestParam("gender") String gender,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "8") int size,
                                 Model model) {

        Page<KhachHang> khachHangs;

        Pageable pageable = PageRequest.of(page - 1, size); // PageRequest.of sử dụng page 0-based

        if ("true".equals(gender)) {
            khachHangs = khachHangService.findByGenderAndTrangThai(true, true, pageable);
        } else if ("false".equals(gender)) {
            khachHangs = khachHangService.findByGenderAndTrangThai(false, true, pageable);
        } else {
            khachHangs = khachHangService.getAllKhachHangByTrangThai(true, pageable);
        }

        model.addAttribute("khachHangs", khachHangs.getContent()); // Lấy danh sách khách hàng từ page hiện tại
        model.addAttribute("currentPage", khachHangs.getNumber() + 1); // Vị trí trang hiện tại (1-based)
        model.addAttribute("totalPages", khachHangs.getTotalPages()); // Tổng số trang
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("selectedGender", gender);

        return "khachHang/left-menu-khachhang"; // Trả về trang JSP để hiển thị kết quả lọc
    }


    @GetMapping("/filterByStatus")
    public String filterByStatus(@RequestParam("status") String status,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "8") int size,
                                 Model model) {
        Page<KhachHang> khachHangs;

        Pageable pageable = PageRequest.of(page - 1, size);

        if ("true".equals(status)) {
            khachHangs = khachHangService.getAllKhachHangByTrangThai(true, pageable);
        } else if ("false".equals(status)) {
            khachHangs = khachHangService.getAllKhachHangByTrangThai(false, pageable);
        } else {
            khachHangs = khachHangService.getAllKhachHangByPage(page, size);
        }

        model.addAttribute("khachHangs", khachHangs.getContent());
        model.addAttribute("currentPage", khachHangs.getNumber() + 1); // Vị trí trang hiện tại (đánh số từ 1)
        model.addAttribute("totalPages", khachHangs.getTotalPages()); // Tổng số trang
        model.addAttribute("status", new KhachHang()); // Đối tượng mới để dùng cho form
        model.addAttribute("selectedStatus", status); // Trạng thái đã chọn để hiển thị trên view


        return "khachHang/left-menu-khachhang"; // Trả về trang JSP để hiển thị kết quả lọc
    }

    @PostMapping("/addAddress")
    public String addAddress(@RequestParam Long khachHangId,
                             @RequestParam String diaChiNhan,
                             @RequestParam String xa,
                             @RequestParam String huyen,
                             @RequestParam String thanhPho) {
        // Tạo địa chỉ mới
        DiaChi diaChi = new DiaChi();
        diaChi.setDiaChiNhan(diaChiNhan);
        diaChi.setXa(xa);
        diaChi.setHuyen(huyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setNgayTao(LocalDate.now());
        diaChi.setTrangThai(true); // Nếu muốn mặc định là true

        // Lấy thông tin KhachHang từ khachHangId
        KhachHang khachHang = khachHangService.getKhachHangById(khachHangId);
        diaChi.setKhachHang(khachHang);

        // Lưu địa chỉ mới vào cơ sở dữ liệu
        diaChiService.saveDiaChi(diaChi);

        return "redirect:/khachhang";
    }


    @PostMapping("/updateAddress")
    public String updateAddress(@ModelAttribute DiaChi diaChi) {
        diaChiService.updateDiaChi(diaChi);
        return "redirect:/khachhang"; // Chuyển hướng về trang danh sách khách hàng sau khi cập nhật địa chỉ
    }


    @GetMapping("/searchByNgaySinh")
    public String searchByNgaySinh(@RequestParam("fromDate") LocalDate fromDate,
                                   @RequestParam("toDate") LocalDate toDate,
                                   Model model) {
        // Implement your search logic here
    List<KhachHang> khachHangs = khachHangService.findKhachHangByNgaySinhBetween(fromDate, toDate);

        // Add the results to the model
        model.addAttribute("khachHangs", khachHangs);
        return "khachHang/left-menu-khachhang"; // Replace with your actual view name
    }


   

}
