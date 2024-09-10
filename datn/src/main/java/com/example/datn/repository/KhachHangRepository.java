package com.example.datn.repository;

import com.example.datn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    boolean existsBySdt(String sdt);

    boolean existsByEmail(String email);

    KhachHang findByEmail(String email);

    KhachHang findByEmailAndSdt(String email, String sdt);

    boolean existsBySdtAndKhachHangIdNot(String sdt, Long excludeId);

    boolean existsByEmailAndKhachHangIdNot(String email, Long excludeId);

    KhachHang findByResetToken(String token);

    KhachHang findKhachHangByEmailAndPassword(String email, String password);

    List<KhachHang> findKhachHangByTrangThaiTrue();

}
