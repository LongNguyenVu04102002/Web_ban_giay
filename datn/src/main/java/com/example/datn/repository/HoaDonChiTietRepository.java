package com.example.datn.repository;

import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    List<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon_TrangThai(SanPhamChiTiet sanPhamChiTiet, Integer trangThai);
}
