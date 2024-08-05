package com.example.datn.repository;

import com.example.datn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    @Query("SELECT k FROM KhachHang k WHERE " +
            "(:sdt IS NULL OR k.sdt LIKE %:sdt%) AND " +
            "(:hoTen IS NULL OR k.hoTen LIKE %:hoTen%) AND " +
            "(:email IS NULL OR k.email LIKE %:email%)")
    List<KhachHang> searchKhachHang(@Param("sdt") String sdt,
                                    @Param("hoTen") String hoTen,
                                    @Param("email") String email);

    boolean existsBySdt(String sdt);

    boolean existsByEmail(String email);

    KhachHang findBySdt(String sdt);
    Optional<KhachHang> findByEmail(String email);

    boolean existsBySdtAndKhachHangIdNot(String sdt, Long excludeId);

    boolean existsByEmailAndKhachHangIdNot(String email, Long excludeId);
}
