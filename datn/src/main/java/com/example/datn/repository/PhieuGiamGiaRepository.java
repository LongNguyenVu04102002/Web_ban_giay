package com.example.datn.repository;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {

    PhieuGiamGia findByMaGiamGia(String maGiamGia);
    boolean existsByMaGiamGia(String maGiamGia);

}
