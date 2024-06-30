package com.example.datn.service.Impl;

import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public ResponseEntity<?> getAllSanPham() {
        return ResponseEntity.ok(sanPhamRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getAllSanPhamById(Long id) {
        return ResponseEntity.ok(sanPhamRepository.findById(id));
    }

}
