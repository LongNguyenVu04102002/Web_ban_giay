package com.example.datn.repository;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {
    Page<PhieuGiamGia> findPhieuGiamGiaByLoaiPhieu(String search, Pageable pageable);
    Page<PhieuGiamGia> findByLoaiPhieu(Integer loaiPhieu, Pageable pageable);

}
