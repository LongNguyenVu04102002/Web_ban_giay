package com.example.datn.service;

import com.example.datn.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getById(Long Id);

    void save(KhachHang khachHang);
    KhachHang toggleTrangThai(Long khachHangId);
    void changeDiaChiStatus(Long diaChiId, boolean newStatus);

    List<KhachHang> searchKhachHang(String sdt, String hoTen, String email);

}
