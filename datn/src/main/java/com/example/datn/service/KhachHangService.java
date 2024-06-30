package com.example.datn.service;

import com.example.datn.entity.KhachHang;
import org.springframework.http.ResponseEntity;

public interface KhachHangService {

    ResponseEntity<?> getAllKhachHang();

    ResponseEntity<?> addKhachHang(KhachHang khachHang);

    ResponseEntity<?> updateKhachHang(KhachHang khachHang, Long id);

    ResponseEntity<?> deleteKhachHang(Long id);

}
