package com.example.datn.repository;

import com.example.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    Optional<NhanVien> findBySdt(String sdt);
    Optional<NhanVien> findByEmail(String email);
}
