package com.example.datn.repository;

import com.example.datn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    List<SanPham> findAllByThuongHieu_ThuongHieuId(Long id);
}
