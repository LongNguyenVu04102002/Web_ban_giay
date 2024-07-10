package com.example.datn.service;

import com.example.datn.entity.SanPham;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAll();

    SanPham getSanPhamById(Long id);

    void save(SanPham sanPham);

    ResponseEntity<?> getAllSanPham();

    ResponseEntity<?> getAllSanPhamById(Long id);

}
