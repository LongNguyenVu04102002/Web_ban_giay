package com.example.datn.service;

import org.springframework.http.ResponseEntity;

public interface SanPhamChiTietService {

    ResponseEntity<?> getAllSanPhamChiTiet();

    ResponseEntity<?> getSanPhamChiTietById(Long id);
}
