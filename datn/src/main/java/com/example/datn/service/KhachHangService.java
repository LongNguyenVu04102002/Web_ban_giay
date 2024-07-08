package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();
    KhachHang getKhachHangById(Long id);
    KhachHang saveKhachHang(KhachHang khachHang);
    KhachHang toggleTrangThai(Long khachHangId);
    Page<KhachHang> getAllKhachHangByPage(int pageNumber, int pageSize);
    KhachHang updateKhachHang(KhachHang khachHang, Long id);
    KhachHang findBySdt(String sdt);
    Page<KhachHang> findByGender(boolean gioiTinh, Pageable pageable);
    Page<KhachHang> findByGenderAndTrangThai(boolean gioiTinh, boolean trangThai, Pageable pageable);
    Page<KhachHang> getAllKhachHangByTrangThai(boolean trangThai, Pageable pageable);
    Page<KhachHang> findKhachHangByNgaySinhBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);
    boolean isSdtExists(String sdt);
    List<KhachHang> searchKhachHang(String keyword);

}
