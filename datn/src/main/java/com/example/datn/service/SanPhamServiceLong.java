package com.example.datn.service;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamServiceLong {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    public SanPham findById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

}
