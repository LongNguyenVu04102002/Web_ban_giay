package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public ResponseEntity<?> getAllKhachHang() {
        return ResponseEntity.ok(khachHangRepository.findAll());
    }

    @Override
    public ResponseEntity<?> addKhachHang(KhachHang khachHang) {
        try {
            khachHangRepository.save(khachHang);
            return ResponseEntity.ok("Save successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateKhachHang(KhachHang khachHang, Long id) {
        try {
            Optional<KhachHang> kh = khachHangRepository.findById(id);
            if (kh.isPresent()) {
                khachHangRepository.save(khachHang);
                return ResponseEntity.ok("Update successfully!");
            } else {
                return ResponseEntity.badRequest().body("KhachHang is not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteKhachHang(Long id) {
        try {
            khachHangRepository.deleteById(id);
            return ResponseEntity.ok("Delete successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
