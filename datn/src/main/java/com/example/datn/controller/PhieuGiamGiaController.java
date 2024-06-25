package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/giamgia")
public class PhieuGiamGiaController {
    @Autowired
    PhieuGiamGiaService service;

    @Autowired
    PhieuGiamGiaRepository repository;
    @GetMapping
    public String listPhieuGiamGia(Model model,
                                   @RequestParam(defaultValue = "") String search,
                                   @RequestParam(defaultValue = "all") String type,
                                   @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);

        Page<PhieuGiamGia> phieuGiamGias = service.filterPhieuGiamGia(search, type, pageable);

        // Cập nhật trạng thái trước khi hiển thị
        for (PhieuGiamGia phieu : phieuGiamGias) {
            String newStatus = phieu.getTrangThaiHienTai();
            if (!newStatus.equals(phieu.getTrangThai())) {
                phieu.setTrangThai(newStatus);
                service.savePhieuGiamGia(phieu);
            }
        }

        model.addAttribute("page", phieuGiamGias);
        model.addAttribute("search", search);
        model.addAttribute("type", type);
        model.addAttribute("currentPage", page);
        return "giamgia/left-menu-phieu";  // Tên của JSP
    }



    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        return "giamgia/addPhieu";
    }

    @PostMapping("/savePhieu")
    public String savePhieuGiamGia(@Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia) {
        service.savePhieuGiamGia(phieuGiamGia);
        return "redirect:/giamgia";
    }

    //
    @PostMapping("/updateTrangThai/{id}")
    public String updateTrangThai(@PathVariable Long phieuGiamGiaId, RedirectAttributes redirectAttributes) {
        Optional<PhieuGiamGia> phieu = repository.findById(phieuGiamGiaId);
        if (phieu.isPresent()) {
            PhieuGiamGia phieuGiamGia = phieu.get();
            phieuGiamGia.setTrangThai("Kết thúc");
            repository.save(phieuGiamGia);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Phiếu giảm giá không tồn tại");
        }
        return "redirect:/giamgia"; // Đổi URL này sang trang bạn muốn chuyển hướng sau khi cập nhật
    }

    @GetMapping("/detail/{phieuGiamGiaId}")
    public String getUpdatePhieuGiamGia(@PathVariable("phieuGiamGiaId") Long phieuGiamGiaId, Model model) {
        Optional<PhieuGiamGia> phieuGiamGia = repository.findById(phieuGiamGiaId);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        return "/giamgia/updatePhieu";
    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updatePhieuGiamGia(@RequestBody PhieuGiamGia phieuGiamGia, @PathVariable Long id){
//        return service.updatePhieuGiamGia(phieuGiamGia, id);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public  ResponseEntity<?> deletePhieuGiamGia(@PathVariable Long id){
//        return service.deletePhieuGiamGia(id);
//    }

}
