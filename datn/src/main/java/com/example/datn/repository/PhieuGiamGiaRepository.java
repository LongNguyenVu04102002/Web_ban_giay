package com.example.datn.repository;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {

    Page<PhieuGiamGia> findByLoaiPhieu(Long loaiPhieu, Pageable pageable);
    Page<PhieuGiamGia> findByTrangThai(String trangThai, Pageable pageable);
}
