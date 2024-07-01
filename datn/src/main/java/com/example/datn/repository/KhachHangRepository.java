package com.example.datn.repository;

import com.example.datn.entity.KhachHang;
import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {


    Page<KhachHang> findKhachHangByTrangThaiTrue(Pageable pageable);
    Page<KhachHang> findByGioiTinh(boolean gioiTinh, Pageable pageable);
    List<KhachHang> findByNgaySinhBetween(LocalDate fromDate, LocalDate toDate);
    List<KhachHang> findBySdt(String sdt);
    Page<KhachHang> findByTrangThai(boolean trangThai, Pageable pageable);
    List<KhachHang> findBySdtContaining(String sdt);
    Page<KhachHang> findByGioiTinhAndTrangThai(boolean gioiTinh, boolean trangThai, Pageable pageable);



}
