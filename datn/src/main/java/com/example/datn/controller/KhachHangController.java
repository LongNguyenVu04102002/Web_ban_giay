package com.example.datn.controller;

import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping
    public ResponseEntity<?> getAllKhachHang() {
        return khachHangService.getAllKhachHang();
    }

    @PostMapping("/save")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangService.addKhachHang(khachHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHang khachHang, @PathVariable Long id) {
        return khachHangService.updateKhachHang(khachHang, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKhachHang(@PathVariable Long id) {
        return khachHangService.deleteKhachHang(id);
    }


}
