package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.Impl.PhieuGiamGiaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaServiceImpl phieuGiamGiaService;

    @Autowired
    private PhieuGiamGiaRepository repository;
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

    @GetMapping("/giamgia/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaService.getById(id);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        return "admin/includes/content/giamgia/form";
    }

    @PostMapping("/giamgia/save")
    public String savePhieuGiamGia(@Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        validatePhieu(phieuGiamGia, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "admin/includes/content/giamgia/form";
        } else {
            String actionMessage = (phieuGiamGia.getPhieuGiamGiaId() == null) ? "Thêm phiếu giảm giá thành công!" : "Cập nhật phiếu giảm giá thành công!";
            phieuGiamGiaService.save(phieuGiamGia);
            redirectAttributes.addFlashAttribute("success", actionMessage);
            return "redirect:/admin/giamgia";
        }
    }


    @PostMapping("/giamgia/update/{id}")
    public String update(@PathVariable Long id) {
        phieuGiamGiaService.update(id);
        return "redirect:/admin/giamgia";
    }

    @GetMapping("/giamgia/{phieuGiamGiaId}/toggle")
    public String toggleTrangThai(@PathVariable Long phieuGiamGiaId, RedirectAttributes redirectAttributes) {
        phieuGiamGiaService.tonggleTrangThaiGiamGia(phieuGiamGiaId);
        redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái thành công!");
        return "redirect:/admin/giamgia";
    }

    private void validatePhieu(PhieuGiamGia phieuGiamGia, BindingResult bindingResult) {

        if (phieuGiamGia.getMaGiamGia() != null && phieuGiamGia.getMaGiamGia().length() > 10) {
            bindingResult.rejectValue("maGiamGia", "Length.phieuGiamGia.maGiamGia", "Không được nhập quá 10 ký tự.");
        }
        if (phieuGiamGia.getPhieuGiamGiaId() == null && phieuGiamGia.getMaGiamGia() != null && repository.existsByMaGiamGia(phieuGiamGia.getMaGiamGia())) {
            bindingResult.rejectValue("maGiamGia", "Duplicate.phieuGiamGia.maGiamGia", "Mã giảm giá đã tồn tại.");
        }
        if (phieuGiamGia.getGiaTriGiam() == null) {
            bindingResult.rejectValue("giaTriGiam", "NotNull.phieuGiamGia.giaTriGiam", "Không được để trống.");
        } else if (phieuGiamGia.getGiaTriGiam() <= 0) {
            bindingResult.rejectValue("giaTriGiam", "NotNull.phieuGiamGia.giaTriGiam", "Giá trị phải lớn hơn 0.");
        }
        // Kiểm tra nếu loại phiếu là 1 thì giá trị giảm không được lớn hơn 100
        // Kiểm tra nếu loại phiếu là true thì giá trị giảm không được lớn hơn 100
        if (phieuGiamGia.isLoaiPhieu() && phieuGiamGia.getGiaTriGiam() > 100) {
            bindingResult.rejectValue("giaTriGiam", "Max.phieuGiamGia.giaTriGiam", "Giá trị giảm phần trăm không được lớn hơn 100.");
        }

        // Kiểm tra số lượng phiếu
        if (phieuGiamGia.getSoLuongPhieu() == null) {
            bindingResult.rejectValue("soLuongPhieu", "NotNull.phieuGiamGia.soLuongPhieu", "Không được để trống.");
        } else if (phieuGiamGia.getSoLuongPhieu() < 0) {
            bindingResult.rejectValue("soLuongPhieu", "Positive.phieuGiamGia.soLuongPhieu", "Giá trị phải lớn hơn 0.");
        }

        // Kiểm tra giá trị đơn tối thiểu
        if (phieuGiamGia.getGiaTriDonToiThieu() == null) {
            bindingResult.rejectValue("giaTriDonToiThieu", "NotNull.phieuGiamGia.giaTriDonToiThieu", "Không được để trống.");
        } else if (phieuGiamGia.getGiaTriDonToiThieu().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("giaTriDonToiThieu", "Positive.phieuGiamGia.giaTriDonToiThieu", "Giá trị phải lớn hơn 0.");
        }

        // Kiểm tra giá trị giảm tối đa
        if (phieuGiamGia.getGiaTriGiamToiDa() == null) {
            bindingResult.rejectValue("giaTriGiamToiDa", "NotNull.phieuGiamGia.giaTriGiamToiDa", "Không được để trống.");
        } else if (phieuGiamGia.getGiaTriGiamToiDa().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("giaTriGiamToiDa", "Positive.phieuGiamGia.giaTriGiamToiDa", "Giá trị phải lớn hơn 0.");
        }

        // Kiểm tra ngày bắt đầu
        if (phieuGiamGia.getNgayBatDau() == null) {
            bindingResult.rejectValue("ngayBatDau", "NotNull.phieuGiamGia.ngayBatDau", "Không được để trống.");
        }

        // Kiểm tra ngày kết thúc
        if (phieuGiamGia.getNgayKetThuc() == null) {
            bindingResult.rejectValue("ngayKetThuc", "NotNull.phieuGiamGia.ngayKetThuc", "Không được để trống.");
        }

        // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
        if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayKetThuc() != null && phieuGiamGia.getNgayBatDau().isAfter(phieuGiamGia.getNgayKetThuc())) {
            bindingResult.rejectValue("ngayKetThuc", "After.phieuGiamGia.ngayKetThuc", "Ngày kết thúc phải sau ngày bắt đầu.");
        }

//        if (phieuGiamGia.getGiaTriGiamToiDa() != null && phieuGiamGia.getTienGiam() != null && phieuGiamGia.getGiaTriGiamToiDa().compareTo(phieuGiamGia.getTienGiam()) > 0) {
//            bindingResult.rejectValue("giaTriGiamToiDa", "Compare.phieuGiamGia.giaTriGiamToiDa", "Giá trị giảm tối đa không được lớn hơn tiền giảm.");
//        }

//        if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayBatDau().isBefore(LocalDate.now())) {
//            bindingResult.rejectValue("ngayBatDau", "Invalid.phieuGiamGia.ngayBatDau", "Ngày bắt đầu không được chọn trước ngày hôm nay.");
//        }
    }
}
