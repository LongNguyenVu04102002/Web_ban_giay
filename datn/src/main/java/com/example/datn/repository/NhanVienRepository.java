package com.example.datn.repository;

import com.example.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    List<NhanVien> findByHoTenContaining(String hoTen);
    List<NhanVien> findByHoTenContainingAndGioiTinh(String hoTen, boolean gioiTinh);
     List<NhanVien> findByGioiTinh(boolean gioiTinh) ;

}
