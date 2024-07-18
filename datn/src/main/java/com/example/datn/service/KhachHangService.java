package com.example.datn.service;

import com.example.datn.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getById(Long Id);

    void save(KhachHang khachHang);
    KhachHang toggleTrangThai(Long khachHangId);

<<<<<<< HEAD
    List<KhachHang> searchKhachHang(String sdt, String hoTen, String email);
=======
>>>>>>> hieu_sp
}
