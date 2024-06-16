package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllKhachHang() {
        return khachHangService.getAllKhachHang();
    }

    @PostMapping("/save")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangService.addKhachHang(khachHang);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHang khachHang, @PathVariable Long id) {
        return khachHangService.updateKhachHang(khachHang, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteKhachHang(@PathVariable Long id) {
        return khachHangService.deleteKhachHang(id);
    }

   
}
