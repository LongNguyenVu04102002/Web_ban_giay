package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.DiaChiService;
import com.example.datn.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import java.util.Collections;
import java.util.List;


@Controller

public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/khachhang")
    public String listKhachHang(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "6") int size) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber()+1);
        model.addAttribute("totalPages", khachHangPage.getTotalPages());
        model.addAttribute("khachHang", new KhachHang());
        return "khachHang/left-menu-khachhang";
    }


    @GetMapping("/khachhang/{khachHangId}/toggle")
    public String toggleTrangThai(@PathVariable Long khachHangId) {
        khachHangService.toggleTrangThai(khachHangId);
        return "redirect:/khachhang";
    }



    @PostMapping("/saveKhachHang")
    public String saveKhachHang(@ModelAttribute("khachHang") @Valid KhachHang khachHang, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // Kiểm tra và thêm lỗi vào bindingResult
        validatePhoneNumber(khachHang, bindingResult);

        // Kiểm tra nếu đang cập nhật (khachHang có khachHangId)
        boolean isUpdate = khachHang.getKhachHangId() != null;

        if (bindingResult.hasErrors()) {
            // Nếu là cập nhật, trả về form cập nhật
            if (isUpdate) {
                model.addAttribute("khachHang", khachHang);
                return "/khachHang/left-menu-update"; // Thay thế bằng tên view cập nhật tương ứng
            } else {
                // Nếu là thêm mới, trả về form thêm mới
                model.addAttribute("khachHang", khachHang);
                return "/khachHang/left-menu-add"; // Thay thế bằng tên view thêm mới tương ứng
            }
        }

        // Cập nhật danh sách địa chỉ cho khách hàng
        if (khachHang.getDiaChiList() != null && !khachHang.getDiaChiList().isEmpty()) {
            for (DiaChi diaChi : khachHang.getDiaChiList()) {
                diaChi.setKhachHang(khachHang); // Gắn địa chỉ với khách hàng
            }
        }

        khachHangService.saveKhachHang(khachHang); // Lưu khách hàng và địa chỉ vào cơ sở dữ liệu

        // Thêm thông báo thành công vào Model
        redirectAttributes.addFlashAttribute("successMessage", "Khách hàng đã được lưu thành công!");

        return "redirect:/khachhang";
    }





    private void validatePhoneNumber(KhachHang khachHang, BindingResult bindingResult) {
        String sdt = khachHang.getSdt();
        int sdtLength = sdt.length();
        Long khachHangId = khachHang.getKhachHangId();

        // Kiểm tra độ dài số điện thoại
        if (sdtLength < 10) {
            bindingResult.rejectValue("sdt", "error.khachHang", "Số điện thoại phải có đủ 10 số");
        } else if (sdtLength > 10) {
            bindingResult.rejectValue("sdt", "error.khachHang", "Số điện thoại chỉ được phép có đúng 10 số");
        } else {
            // Kiểm tra xem số điện thoại đã tồn tại và không phải của khách hàng hiện tại
            KhachHang existingKhachHang = khachHangService.findBySdt(sdt);
            if (existingKhachHang != null && !existingKhachHang.getKhachHangId().equals(khachHangId)) {
                bindingResult.rejectValue("sdt", "error.khachHang", "Số điện thoại đã tồn tại");
            }
        }
    }


    @PostMapping("/searchBySDT")
    public String searchCustomersBySDT(@RequestParam("sdt") String sdt, Model model) {
        KhachHang searchResult = khachHangService.findBySdt(sdt);

        if (searchResult != null) {
            model.addAttribute("khachHangs", List.of(searchResult));
            model.addAttribute("successMessage", "Customer found successfully.");
        } else {
            model.addAttribute("khachHangs", Collections.emptyList());
            model.addAttribute("errorMessage", "No customer found with the given phone number.");
        }

        return "khachHang/left-menu-khachhang";
    }



    @GetMapping("/filterByGender")
    public String filterByGender(@RequestParam("gender") String gender,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 Model model) {

        Page<KhachHang> khachHangs;

        Pageable pageable = PageRequest.of(page - 1, size);

        if ("true".equals(gender)) {
            khachHangs = khachHangService.findByGenderAndTrangThai(true, true, pageable);
        } else if ("false".equals(gender)) {
            khachHangs = khachHangService.findByGenderAndTrangThai(false, true, pageable);
        } else {
            khachHangs = khachHangService.getAllKhachHangByTrangThai(true, pageable);
        }

        model.addAttribute("khachHangs", khachHangs.getContent());
        model.addAttribute("currentPage", khachHangs.getNumber() + 1);
        model.addAttribute("totalPages", khachHangs.getTotalPages());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("selectedGender", gender);

        return "khachHang/left-menu-khachhang";
    }


    @GetMapping("/filterByStatus")
    public String filterByStatus(@RequestParam("status") String status,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "6") int size,
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


        return "khachHang/left-menu-khachhang";
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
        return "redirect:/khachhang";
    }


    @GetMapping("/searchByNgaySinh")
    public String searchByNgaySinh(@RequestParam("fromDate") LocalDate fromDate,
                                   @RequestParam("toDate") LocalDate toDate,
                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "size", defaultValue = "6") int size,
                                   Model model) {
        Page<KhachHang> khachHangPage = khachHangService.findKhachHangByNgaySinhBetween(fromDate, toDate, PageRequest.of(page - 1, size));

        List<KhachHang> khachHangs = khachHangPage.getContent();
        int totalPages = khachHangPage.getTotalPages();
        long totalItems = khachHangPage.getTotalElements();
        int currentPage = khachHangPage.getNumber() + 1;

        model.addAttribute("khachHangs", khachHangs);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        return "khachHang/left-menu-khachhang"; // Thay thế với tên view thực tế của bạn
    }


    @GetMapping("/themMoi")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "/khachHang/left-menu-add";
    }


    @GetMapping("/editKhachHang/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        KhachHang khachHang = khachHangService.getKhachHangById(id);

        if (khachHang == null) {
            model.addAttribute("errorMessage", "Khách hàng không tồn tại");
            return "redirect:/khachhang";
        }

        model.addAttribute("khachHang", khachHang);
        return "/khachHang/left-menu-update"; // Tên của JSP file chứa form cập nhật
    }




}
