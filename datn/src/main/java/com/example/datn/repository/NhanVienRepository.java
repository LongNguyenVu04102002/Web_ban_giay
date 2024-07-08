package com.example.datn.repository;

import com.example.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    List<NhanVien> findByHoTenContaining(String hoTen);
    List<NhanVien> findBySdtContainingAndGioiTinh(String sdt, Boolean gioiTinh);
    List<NhanVien> findBySdtContaining(String sdt);
    List<NhanVien> findByGioiTinh(Boolean gioiTinh);
    @Query("SELECT n FROM NhanVien n WHERE n.hoTen LIKE %:keyword% " +
            "OR n.sdt LIKE %:keyword% " +
            "OR n.email LIKE %:keyword% " +
            "OR n.role LIKE %:keyword%")
    List<NhanVien> searchNhanViensByKeyword(@Param("keyword") String keyword);
}
