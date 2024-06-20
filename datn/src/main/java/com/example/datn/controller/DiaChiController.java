package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiaChiController {

    @Autowired
    DiaChiService diaChiService;

    @GetMapping("/diachi")
    public String listDiaChi(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "3") int size) {
        Page<DiaChi> diaChiPage = diaChiService.getAllDiaChiByPage(page, size);
        model.addAttribute("diaChis", diaChiPage.getContent());
        model.addAttribute("currentPage", diaChiPage.getNumber() + 1); // Vị trí trang hiện tại
        model.addAttribute("totalPages", diaChiPage.getTotalPages()); // Tổng số trang
        model.addAttribute("diaChi", new KhachHang()); // Thêm đối tượng khachHang vào model
        return "left-menu-dia-chi"; // Trả về tên của view (khachhang.jsp)
    }
    @PostMapping("/save")
    public ResponseEntity<?> addDiaCHi(@RequestBody DiaChi diaChi){
        return diaChiService.addDiaChi(diaChi);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDiaChi(@RequestBody DiaChi diaChi,@PathVariable Long id){
        return diaChiService.updateDiaChi(diaChi,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDiaChi(@PathVariable Long id){
        return diaChiService.deleteDiaChi(id);
    }
}
