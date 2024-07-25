package com.example.datn.repository;

import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    List<SanPhamChiTiet> findBySanPham(SanPham sanPham);
    List<SanPhamChiTiet> findBySanPhamSanPhamId(Long sanPhamId);
}
