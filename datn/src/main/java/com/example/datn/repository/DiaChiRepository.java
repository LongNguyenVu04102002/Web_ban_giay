package com.example.datn.repository;

import com.example.datn.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {
    List<DiaChi> findByKhachHang_KhachHangId(Long khachHangId);
}