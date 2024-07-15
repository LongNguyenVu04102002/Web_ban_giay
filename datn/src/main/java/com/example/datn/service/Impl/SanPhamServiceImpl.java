package com.example.datn.service.Impl;

import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<SanPhamChiTiet> uniqueSizes(List<SanPhamChiTiet> sanPhamChiTietList) {
        Set<String> sizes = new HashSet<>();
        return sanPhamChiTietList.stream()
                .sorted(Comparator.comparing(chiTiet -> chiTiet.getKichThuoc().getTen()))
                .filter(chiTiet -> sizes.add(chiTiet.getKichThuoc().getTen()))
                .collect(Collectors.toList());
    }

    public List<SanPhamChiTiet> uniqueColor(List<SanPhamChiTiet> sanPhamChiTietList) {
        Set<String> color = new HashSet<>();
        return sanPhamChiTietList.stream()
                .sorted(Comparator.comparing(chiTiet -> chiTiet.getMauSac().getTen()))
                .filter(chiTiet -> color.add(chiTiet.getMauSac().getTen()))
                .collect(Collectors.toList());
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
