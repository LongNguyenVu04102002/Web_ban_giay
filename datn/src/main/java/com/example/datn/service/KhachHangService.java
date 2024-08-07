package com.example.datn.service;

import com.example.datn.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getById(Long Id);

    KhachHang save(KhachHang khachHang);

    KhachHang toggleTrangThai(Long khachHangId);

    KhachHang update(KhachHang khachHang);

    boolean isSdtExist(String sdt);

    boolean isEmailExist(String email);

    boolean isPhoneNumberDuplicate(String sdt, Long excludeId);

    boolean isEmailDuplicate(String email, Long excludeId);

}
