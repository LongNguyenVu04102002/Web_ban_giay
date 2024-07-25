package com.example.datn.service;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietLongService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    public SanPhamChiTiet findById(Long id) {
        return sanPhamChiTietRepository.findById(id).orElse(null);
    }

    public List<SanPhamChiTiet> findBySanPhamId(Long sanPhamId) {
        return sanPhamChiTietRepository.findBySanPhamSanPhamId(sanPhamId);
    }
}
