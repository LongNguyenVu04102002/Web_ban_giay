package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.service.Impl.PhieuGiamGiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaServiceImpl phieuGiamGiaService;

    @GetMapping("/giamgia")
    public String getAll(Model model) {
        List<PhieuGiamGia> phieuGiamGiaList = phieuGiamGiaService.getAll();
        model.addAttribute("phieuGiamGiaList", phieuGiamGiaList);
        return "admin/includes/content/giamgia/home";
    }

    @GetMapping("/giamgia/form")
    public String viewForm(Model model) {
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        return "admin/includes/content/giamgia/form";
    }

    @PostMapping("/giamgia/save")
    public String savePhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        phieuGiamGiaService.save(phieuGiamGia);
        return "redirect:/admin/giamgia";
    }

    @GetMapping("/giamgia/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaService.getById(id);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        return "admin/includes/content/giamgia/form";
    }
<<<<<<< HEAD
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

//        if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayBatDau().isBefore(LocalDate.now())) {
//            bindingResult.rejectValue("ngayBatDau", "Invalid.phieuGiamGia.ngayBatDau", "Ngày bắt đầu không được chọn trước ngày hôm nay.");
//        }
    }

//    @GetMapping("/search")
//    public String searchAllFields(@RequestParam(name = "page", defaultValue = "0") int page,
//                                  @RequestParam("query") String query,
//                                  Model model) {
////        // Số lượng phần tử trên mỗi trang
////        int pageSize = 10;
////
////        // Tìm kiếm và lấy dữ liệu phân trang
////        Page<PhieuGiamGia> searchResults = service.searchAllFields(query, PageRequest.of(page, pageSize));
////
////        // Các thông tin cần thiết để trả về trang JSP
////        model.addAttribute("searchResults", searchResults.getContent());
////        model.addAttribute("currentPage", page);
////        model.addAttribute("totalPages", searchResults.getTotalPages());
////        model.addAttribute("query", query);
////
////        return "giamgia"; // Tên của trang JSP để hiển thị kết quả tìm kiếm và phân trang
//
//        Page<PhieuGiamGia> phieuGiamGias;
//        if (page < 1) page = 1;
//        Pageable pageable = PageRequest.of(page - 1, 5);
//        phieuGiamGias = service.searchAllFields(query, pageable);
//        model.addAttribute("page", phieuGiamGias);
//        return "giamgia/left-menu-phieu";
//    }


=======
>>>>>>> master

}
