package com.example.datn.service;

import com.example.datn.entity.KhachHang;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getById(Long Id);

    KhachHang save(KhachHang khachHang);

    KhachHang toggleTrangThai(Long khachHangId);

    KhachHang update(KhachHang khachHang, BindingResult result);

    boolean isSdtExist(String sdt);


    boolean isEmailExist(String email);

    boolean isPhoneNumberDuplicate(String sdt, Long excludeId);

    boolean isEmailDuplicate(String email, Long excludeId);


    KhachHang findByResetToken(String token);

    KhachHang findByEmailAndSdt(String email, String sdt);

    KhachHang login (String email, String password);

    List<KhachHang> findKhachHangByTrangThaiTrue();
    boolean isEmailOrPhoneExist(String email, String sdt);
    void register(String username, String email, String sdt, boolean gioiTinh, LocalDate ngaySinh, String password,
                  String diaChiStr, String xa, String huyen, String thanhPho, String diaChiSdt, String diaChiTen);

}
