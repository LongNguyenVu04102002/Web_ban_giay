package com.example.datn.service;

import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SanPhamChiTietService {

    ResponseEntity<?> getAllSanPhamChiTiet();

    ResponseEntity<?> getSanPhamChiTietById(Long id);

    List<SanPhamChiTiet> getALL();
}
