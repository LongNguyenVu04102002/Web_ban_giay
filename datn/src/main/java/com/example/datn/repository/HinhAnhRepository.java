package com.example.datn.repository;

import com.example.datn.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
    Optional<HinhAnh> findBySanPhamChiTietSanPhamChiTietIdAndUuTien(Long sanPhamChiTietId, Integer uuTien);
}
