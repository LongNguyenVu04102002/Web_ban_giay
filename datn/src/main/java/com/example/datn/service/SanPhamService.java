package com.example.datn.service;

import com.example.datn.entity.SanPham;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SanPhamService {

    ResponseEntity<?> getAllSanPham();

    ResponseEntity<?> getById(Long id);

    List<SanPham> getAll();

    SanPham getSanPhamById(Long id);

    void save(SanPham sanPham);

    void update(Long id);

}
