package com.example.datn.service.Impl;

import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<SanPhamChiTiet> getAll() {
        return sanPhamChiTietRepository.findAll();
    }

    @Override
    public SanPhamChiTiet getById(Long id) {
        return sanPhamChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public void save(List<SanPhamChiTiet> sanPhamChiTietList) {
        for (SanPhamChiTiet spct : sanPhamChiTietList) {
            if (spct.getBarCode() == null || spct.getBarCode().isEmpty()) {
                spct.setBarCode(generateBarCode());
            }
        }
        sanPhamChiTietRepository.saveAll(sanPhamChiTietList);
    }

    @Override
    public void saveOfUpdate(SanPhamChiTiet sanPhamChiTiet) {
        sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    @Override
    public void update(Long id) {
        Optional<SanPhamChiTiet> sanPhamChiTiet = sanPhamChiTietRepository.findById(id);
        if(sanPhamChiTiet.isPresent()){
            SanPhamChiTiet spct = sanPhamChiTiet.get();
            spct.setTrangThai(!spct.isTrangThai());
            sanPhamChiTietRepository.save(spct);
        }
    }

    @Override
    public BigDecimal getPrice(Long sanPhamId, Long sizeId, Long colorId) {
        return sanPhamChiTietRepository.findPriceBySizeAndColor(sanPhamId,sizeId,colorId);
    }

    private String generateBarCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
    }

}
