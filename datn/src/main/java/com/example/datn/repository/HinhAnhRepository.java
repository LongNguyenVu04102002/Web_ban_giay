package com.example.datn.repository;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
    Optional<HinhAnh> findBySanPhamChiTietSanPhamChiTietIdAndUuTien(Long sanPhamChiTietId, Integer uuTien);

    List<HinhAnh> findBySanPhamChiTietSanPhamChiTietId(Long sanPhamChiTietId);

    List<HinhAnh> findBySanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);

    List<HinhAnh> findBySanPhamChiTietSanPhamChiTietIdAndUuTienGreaterThan(Long sanPhamChiTietId, int uuTien);

}
