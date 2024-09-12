package com.example.datn.repository;

import com.example.datn.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {
    @Query("SELECT COUNT(d) FROM DiaChi d WHERE d.khachHang.khachHangId = :khachHangId")
    long countByKhachHangId(@Param("khachHangId") Long khachHangId);
}
