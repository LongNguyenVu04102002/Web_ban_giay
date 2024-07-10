package com.example.datn.service.Impl;

import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public ResponseEntity<?> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(sanPhamChiTietRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getSanPhamChiTietById(Long id) {
        return ResponseEntity.ok(sanPhamChiTietRepository.findById(id));
    }

    @Override
    public List<SanPhamChiTiet> getALL() {
        return sanPhamChiTietRepository.findAll();
    }

}
