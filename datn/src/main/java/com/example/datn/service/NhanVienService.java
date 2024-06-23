package com.example.datn.service;
import com.example.datn.entity.NhanVien;
import org.springframework.http.ResponseEntity;

public interface NhanVienService {
    ResponseEntity<?> getAllNhanVien();

    ResponseEntity<?> addNhanVien(NhanVien nhanVien);

    ResponseEntity<?> updateNhanVien(NhanVien nhanVien, Long id);

    ResponseEntity<?> deleteNhanVien(Long id);
}
