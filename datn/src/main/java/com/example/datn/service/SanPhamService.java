package com.example.datn.service;

import org.springframework.http.ResponseEntity;

public interface SanPhamService {

    ResponseEntity<?> getAllSanPham();

    ResponseEntity<?> getAllSanPhamById(Long id);

}
