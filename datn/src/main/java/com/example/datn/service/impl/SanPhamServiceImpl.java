package com.example.datn.service.Impl;

import com.example.datn.entity.SanPham;
import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getSanPhamById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    @Override
    public ResponseEntity<?> getAllSanPham() {
        return ResponseEntity.ok(sanPhamRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getAllSanPhamById(Long id) {
        return ResponseEntity.ok(sanPhamRepository.findById(id));
    }

}
