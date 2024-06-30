package com.example.datn.api;

import com.example.datn.entity.KhachHang;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/khachhang")
public class KhachHangApi {

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllKhachHang() {
        return khachHangService.getAllKhachHang();
    }

    @PostMapping("/save")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHang khachHang) {
        return ResponseEntity.ok(khachHangService.addKhachHang(khachHang));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHang khachHang, @PathVariable Long id) {
        return ResponseEntity.ok(khachHangService.updateKhachHang(khachHang,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteKhachHang(@PathVariable Long id){
        return ResponseEntity.ok(khachHangService.deleteKhachHang(id));
    }

}
