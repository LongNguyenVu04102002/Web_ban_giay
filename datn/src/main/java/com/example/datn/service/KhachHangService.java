package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    Optional<KhachHang> getKhachHangById(Long id);

    KhachHang saveKhachHang(KhachHang khachHang);

//    ResponseEntity<?> updateKhachHang(KhachHang khachHang, Long id);

    void deleteKhachHang(Long id);
    KhachHang toggleTrangThai(Long khachHangId);

}
