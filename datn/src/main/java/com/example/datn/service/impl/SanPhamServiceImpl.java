package com.example.datn.service.Impl;

import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamChiTietRepository chiTietRepository;
    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getSanPhamById(Long id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            SanPham sanPham = optionalSanPham.get();
            List<SanPhamChiTiet> chiTietList = chiTietRepository.findBySanPham(sanPham);
            sanPham.setSanPhamChiTietList(chiTietList);
            return sanPham;
        } else {
            return null;
        }
    }

    @Override
    public void save(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    @Override
    public void update(Long id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        if(sanPham.isPresent()){
            SanPham sp = sanPham.get();
            sp.setTrangThai(!sp.isTrangThai());
            sanPhamRepository.save(sp);
        }
    }



}
