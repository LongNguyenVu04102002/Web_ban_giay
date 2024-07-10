package com.example.datn.controller;

import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.service.DiaChiService;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> KhachHang_Long

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
        return "admin/includes/content/khachhang/form";
    }

    @GetMapping("/khachhang/form")
    public String viewAdd(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "admin/includes/content/khachhang/form";
    }

    @PostMapping("/khachhang/save")
    public String save(KhachHang khachHang) {
        khachHangService.save(khachHang);
        return "redirect:/admin/taikhoan/khachhang";
    }
    
<<<<<<< HEAD
    @GetMapping("/searchKhachHang")
    public String searchKhachHang(@RequestParam("keyword") String keyword, Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "6") int size) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        List<KhachHang> khachHangs = khachHangService.searchKhachHang(keyword);
        model.addAttribute("khachHangs", khachHangs);
        model.addAttribute("currentPage", khachHangPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", khachHangPage.getTotalPages());
        model.addAttribute("khachHang", new KhachHang());
        return "khachHang/left-menu-khachhang"; // JSP file to display results
    }


    @GetMapping("/filterByGender")
    public String filterByGender(@RequestParam("gender") String gender,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 Model model) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);

        Pageable pageable = PageRequest.of(page - 1, size);

        if ("true".equals(gender)) {
            khachHangPage = khachHangService.findByGenderAndTrangThai(true, true, pageable);
        } else if ("false".equals(gender)) {
            khachHangPage = khachHangService.findByGenderAndTrangThai(false, true, pageable);
        } else {
            khachHangPage = khachHangService.getAllKhachHangByTrangThai(true, pageable);
        }

        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", khachHangPage.getTotalPages());
        model.addAttribute("khachHang", new KhachHang());

        return "khachHang/left-menu-khachhang";
    }



    @GetMapping("/filterByStatus")
    public String filterByStatus(@RequestParam("status") String status,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 Model model) {
        Page<KhachHang> khachHangPage = khachHangService.getAllKhachHangByPage(page, size);

        Pageable pageable = PageRequest.of(page - 1, size);

        if ("true".equals(status)) {
            khachHangPage = khachHangService.getAllKhachHangByTrangThai(true, pageable);
        } else if ("false".equals(status)) {
            khachHangPage = khachHangService.getAllKhachHangByTrangThai(false, pageable);
        } else {
            khachHangPage = khachHangService.getAllKhachHangByPage(page, size);
        }

        model.addAttribute("khachHangs", khachHangPage.getContent());
        model.addAttribute("currentPage", khachHangPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", khachHangPage.getTotalPages());
        model.addAttribute("khachHang", new KhachHang()); // Trạng thái đã chọn để hiển thị trên view


        return "khachHang/left-menu-khachhang";
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
    public String updateAddress(@ModelAttribute DiaChi diaChi,
                                @RequestParam int currentPage,
                                @RequestParam int pageSize) {
        diaChiService.updateDiaChi(diaChi);
        return "redirect:/khachhang?page=" + currentPage + "&size=" + pageSize;
    }






    @GetMapping("/themMoi")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "/khachHang/left-menu-add";
    }


    @GetMapping("/editKhachHang/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model,   @RequestParam int page,
                                 @RequestParam int size) {
        KhachHang khachHang = khachHangService.getKhachHangById(id);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        if (khachHang == null) {
            model.addAttribute("errorMessage", "Khách hàng không tồn tại");
            return "redirect:/khachhang";
        }

        model.addAttribute("khachHang", khachHang);
        return "/khachHang/left-menu-update"; // Tên của JSP file chứa form cập nhật
    }




    @PostMapping("/updateKhachHang/{id}")
    public String updateKhachHang(@Valid @ModelAttribute KhachHang khachHang,
                                  BindingResult bindingResult,
                                  @PathVariable Long id,
                                  @RequestParam(defaultValue = "1") int currentPage,
                                  @RequestParam(defaultValue = "6") int pageSize,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHang", khachHang);
            return "khachHang/left-menu-update";  // Tên trang JSP của bạn
        }
        khachHangService.updateKhachHang(khachHang, id);
        return "redirect:/khachhang?page=" + currentPage + "&size=" + pageSize;
    }

=======
>>>>>>> KhachHang_Long


}
