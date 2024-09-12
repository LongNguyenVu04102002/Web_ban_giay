package com.example.datn.repository;

import com.example.datn.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {
    @Query("SELECT COUNT(d) FROM DiaChi d WHERE d.khachHang.khachHangId = :khachHangId")
    long countByKhachHangId(@Param("khachHangId") Long khachHangId);

    @Query("select p from DiaChi p where p.khachHang.khachHangId = :khachHangId")
    List<DiaChi> findAllByKhachHangId(Long khachHangId);
}
