package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/giamgia")
public class PhieuGiamGiaController {
    @Autowired
    PhieuGiamGiaService service;

    @Autowired
    PhieuGiamGiaRepository repository;

    @GetMapping
    public String getAllPhieu(Model model, @RequestParam(defaultValue = "1") int page) {
    Page<PhieuGiamGia> phieuGiamGias;
    if (page < 1) page = 1;
    Pageable pageable = PageRequest.of(page - 1, 5);
    phieuGiamGias = service.getAllPhieu(pageable);
    model.addAttribute("page", phieuGiamGias);
    return "giamgia/left-menu-phieu";
}


    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        return "giamgia/left-menu-addPhieu";
    }

    @PostMapping("/savePhieu")
    public String savePhieuGiamGia(@Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        validatePhieu(phieuGiamGia, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "giamgia/left-menu-addPhieu";
        } else {
            service.savePhieuGiamGia(phieuGiamGia);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm phiếu giảm giá thành công!");
            return "redirect:/giamgia";
        }
    }


    //
    @PostMapping("/updateTrangThai/{phieuGiamGiaId}")
    @ResponseBody
    public ResponseEntity<String> updateTrangThai(@PathVariable Long phieuGiamGiaId, @RequestParam boolean checked) {
        try {
            PhieuGiamGia phieuGiamGia = service.getPhieuById(phieuGiamGiaId);
            if (phieuGiamGia == null) {
                return ResponseEntity.notFound().build();
            }

            // Update status if the current status is "Đang diễn ra"
            if (phieuGiamGia.getTrangThai().equals("Đang diễn ra")) {
                phieuGiamGia.setTrangThai("Kết thúc");
                service.savePhieuGiamGia(phieuGiamGia);
            }

            return ResponseEntity.ok("Trạng thái đã được cập nhật.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình cập nhật trạng thái.");
        }
    }


    @GetMapping("/detail/{phieuGiamGiaId}")
    public String getUpdatePhieuGiamGia(@PathVariable("phieuGiamGiaId") Long phieuGiamGiaId, Model model) {
        PhieuGiamGia phieuGiamGia = service.getPhieuById(phieuGiamGiaId);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        return "/giamgia/left-menu-updatePhieu";
    }
    @PostMapping("/update/{id}")
    public String updatePhieuGiamGia(@Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia,
                                     @PathVariable Long id,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        // Validate the object
        validatePhieu(phieuGiamGia, bindingResult);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.phieuGiamGia", bindingResult);
            redirectAttributes.addFlashAttribute("phieuGiamGia", phieuGiamGia); // Keep the form data for user convenience
            return "giamgia/left-menu-updatePhieu";
        } else {
            // Update the PhieuGiamGia
            service.update(phieuGiamGia, id);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật " +
                    "phiếu giảm giá thành công!");
            return "redirect:/giamgia";
        }
    }



    @GetMapping("/searchLoaiPhieu")
    public String searchLoaiPhieu(@RequestParam(value = "type", required = false, defaultValue = "0") String type,
                                  @RequestParam(defaultValue = "1") int page, Model model) {
        Page<PhieuGiamGia> phieuGiamGias;
        Pageable pageable = PageRequest.of(page - 1, 5); // 5 là số lượng item mỗi trang

        if ("0".equals(type)) {
            phieuGiamGias = service.getAllPhieu(pageable);
        } else {
            Long loaiPhieu = Long.parseLong(type);
            phieuGiamGias = service.searchLoaiPhieu(loaiPhieu, pageable);
        }

        model.addAttribute("listPhieu", phieuGiamGias.getContent());
        model.addAttribute("page", phieuGiamGias);
        model.addAttribute("type", type);

        return "giamgia/left-menu-phieu"; // Tên của view JSP của bạn
    }

    @GetMapping("/searchTrangThai")
    public String searchTrangThai(@RequestParam(value = "status", required = false, defaultValue = "all") String status,
                                  @RequestParam(defaultValue = "1") int page, Model model) {
        Page<PhieuGiamGia> phieuGiamGias;
        Pageable pageable = PageRequest.of(page - 1, 5); // 5 là số lượng item mỗi trang

        if ("all".equals(status)) {
            phieuGiamGias = service.getAllPhieu(pageable);
        } else {
            phieuGiamGias = service.searchTrangThai(status, pageable);
        }

        model.addAttribute("listPhieu", phieuGiamGias.getContent());
        model.addAttribute("page", phieuGiamGias);
        model.addAttribute("status", status);

        return "giamgia/left-menu-phieu"; // Tên của view JSP của bạn
    }

    @GetMapping("/searchGiaTriDonToiThieu")
    public String searchDonToiThieu(@RequestParam(value = "toiThieu", required = false, defaultValue = "all") String toiThieu,
                                    @RequestParam(defaultValue = "1") int page, Model model) {
        Page<PhieuGiamGia> phieuGiamGias = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        BigDecimal donToiThieuMin;
        BigDecimal donToiThieuMax;

        if("all".equals(toiThieu)){
            phieuGiamGias = service.getAllPhieu(pageable);
        } else if ("0-500000".equals(toiThieu)){
            donToiThieuMin = new BigDecimal("0");
            donToiThieuMax = new BigDecimal("500000");
            phieuGiamGias = service.searchDonToiThieu(donToiThieuMin, donToiThieuMax, pageable);
        } else if ("500000-10000000".equals(toiThieu)){
            donToiThieuMin = new BigDecimal("500000");
            donToiThieuMax = new BigDecimal("10000000");
            phieuGiamGias = service.searchDonToiThieu(donToiThieuMin, donToiThieuMax, pageable);
        }else if (">10000000".equals(toiThieu)){
            donToiThieuMin = new BigDecimal("10000000");
            donToiThieuMax = new BigDecimal("999999999999999");
            phieuGiamGias = service.searchDonToiThieu(donToiThieuMin, donToiThieuMax, pageable);
        }
        if (phieuGiamGias != null) {
            model.addAttribute("listPhieu", phieuGiamGias.getContent());
            model.addAttribute("page", phieuGiamGias);
            model.addAttribute("toiThieu", toiThieu);
        }

        return "giamgia/left-menu-phieu";
    }

    @GetMapping("/searchByDateRange")
    public String searchByDateRange(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                    @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                    @RequestParam(defaultValue = "1") int page, Model model) {
        Page<PhieuGiamGia> phieuGiamGias = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        if (from != null && to != null) {
            phieuGiamGias = service.searchDateRange(from, to, pageable);
        }
        if(phieuGiamGias != null) {
            model.addAttribute("listPhieu", phieuGiamGias.getContent());
            model.addAttribute("page", phieuGiamGias);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
        }
        return "giamgia/left-menu-phieu";
    }



    private void validatePhieu(PhieuGiamGia phieuGiamGia, BindingResult bindingResult) {
        // Kiểm tra trường hợp loại phiếu là tiền mặt
        if (phieuGiamGia.getLoaiPhieu() == 1 && (phieuGiamGia.getTienGiam() == null)) {
            bindingResult.rejectValue("tienGiam", "NotNull.phieuGiamGia.tienGiam", "Tiền giảm không được để trống.");
        }

        // Kiểm tra trường hợp loại phiếu là phần trăm
        if (phieuGiamGia.getLoaiPhieu() == 2 && (phieuGiamGia.getPhanTramGiam() == null)) {
            bindingResult.rejectValue("phanTramGiam", "NotNull.phieuGiamGia.phanTramGiam", "Phần trăm giảm không được để trống.");
        } else if(phieuGiamGia.getLoaiPhieu() == 2 && (phieuGiamGia.getPhanTramGiam() == null || phieuGiamGia.getPhanTramGiam() <= 0 || phieuGiamGia.getPhanTramGiam() > 100)) {
            bindingResult.rejectValue("phanTramGiam", "Range.phieuGiamGia.phanTramGiam", "Phần trăm giảm phải nằm trong khoảng từ 1 đến 100.");
        }


        // Kiểm tra số lượng phiếu
        if (phieuGiamGia.getSoLuongPhieu() == null) {
            bindingResult.rejectValue("soLuongPhieu", "NotNull.phieuGiamGia.soLuongPhieu", "Số lượng phiếu không được để trống.");
        } else if (phieuGiamGia.getSoLuongPhieu() <= 0) {
            bindingResult.rejectValue("soLuongPhieu", "Positive.phieuGiamGia.soLuongPhieu", "Số lượng phiếu phải lớn hơn 0.");
        }

        // Kiểm tra giá trị đơn tối thiểu
        if (phieuGiamGia.getGiaTriDonToiThieu() == null) {
            bindingResult.rejectValue("giaTriDonToiThieu", "NotNull.phieuGiamGia.giaTriDonToiThieu", "Giá trị đơn tối thiểu không được để trống.");
        } else if (phieuGiamGia.getGiaTriDonToiThieu().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("giaTriDonToiThieu", "Positive.phieuGiamGia.giaTriDonToiThieu", "Giá trị đơn tối thiểu phải lớn hơn 0.");
        }

        // Kiểm tra giá trị giảm tối đa
        if (phieuGiamGia.getGiaTriGiamToiDa() == null) {
            bindingResult.rejectValue("giaTriGiamToiDa", "NotNull.phieuGiamGia.giaTriGiamToiDa", "Giảm tối đa không được để trống.");
        } else if (phieuGiamGia.getGiaTriGiamToiDa().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("giaTriGiamToiDa", "Positive.phieuGiamGia.giaTriGiamToiDa", "Giảm tối đa phải lớn hơn 0.");
        }

        // Kiểm tra ngày bắt đầu
        if (phieuGiamGia.getNgayBatDau() == null) {
            bindingResult.rejectValue("ngayBatDau", "NotNull.phieuGiamGia.ngayBatDau", "Ngày bắt đầu không được để trống.");
        }

        // Kiểm tra ngày kết thúc
        if (phieuGiamGia.getNgayKetThuc() == null) {
            bindingResult.rejectValue("ngayKetThuc", "NotNull.phieuGiamGia.ngayKetThuc", "Ngày kết thúc không được để trống.");
        }

        // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
        if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayKetThuc() != null && phieuGiamGia.getNgayBatDau().isAfter(phieuGiamGia.getNgayKetThuc())) {
            bindingResult.rejectValue("ngayKetThuc", "After.phieuGiamGia.ngayKetThuc", "Ngày kết thúc phải sau ngày bắt đầu.");
        }

        if (phieuGiamGia.getGiaTriGiamToiDa() != null && phieuGiamGia.getTienGiam() != null && phieuGiamGia.getGiaTriGiamToiDa().compareTo(phieuGiamGia.getTienGiam()) > 0) {
            bindingResult.rejectValue("giaTriGiamToiDa", "Compare.phieuGiamGia.giaTriGiamToiDa", "Giá trị giảm tối đa không được lớn hơn tiền giảm.");
        }

        if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayBatDau().isBefore(LocalDate.now())) {
            bindingResult.rejectValue("ngayBatDau", "Invalid.phieuGiamGia.ngayBatDau", "Ngày bắt đầu không được chọn trước ngày hôm nay.");
        }
    }


}
