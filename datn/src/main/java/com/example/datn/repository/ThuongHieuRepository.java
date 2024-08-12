package com.example.datn.repository;

import com.example.datn.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {

    Optional<ThuongHieu> findByTen(String ten);

    List<ThuongHieu> findAllByTenAndThuongHieuIdNot(String ten, Long thuongHieuId);

    List<ThuongHieu> findByTrangThai(boolean trangThai);
}
