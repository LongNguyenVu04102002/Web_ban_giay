package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getKhachHangById(Long id);

    KhachHang saveKhachHang(KhachHang khachHang);

//    ResponseEntity<?> updateKhachHang(KhachHang khachHang, Long id);

    void deleteKhachHang(Long id);
    KhachHang toggleTrangThai(Long khachHangId);
    Page<KhachHang> getAllKhachHangByPage(int pageNumber, int pageSize);
    KhachHang findById(Long khachHangId);

    KhachHang updateKhachHang(KhachHang khachHang, Long id);
    List<KhachHang> findBySdt(String sdt);
}
