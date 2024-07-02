package com.example.datn.repository;

import com.example.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    List<NhanVien> findByHoTenContaining(String hoTen);
    List<NhanVien> findBySdtContainingAndGioiTinh(String sdt, Boolean gioiTinh);
    List<NhanVien> findBySdtContaining(String sdt);
    List<NhanVien> findByGioiTinh(Boolean gioiTinh);

}
