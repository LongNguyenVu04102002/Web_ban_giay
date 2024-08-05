package com.example.datn.service;

import com.example.datn.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getById(Long Id);

    KhachHang  save(KhachHang khachHang);
    KhachHang toggleTrangThai(Long khachHangId);


    KhachHang update(KhachHang khachHang);
    boolean isSdtExist(String sdt);

    boolean isEmailExist(String email);

    boolean isPhoneNumberDuplicate(String sdt, Long excludeId);

    boolean isEmailDuplicate(String email, Long excludeId);
<<<<<<< HEAD
}
=======
void registerNewKhachHang(String email, String password, String hoTen, String sdt, LocalDate ngaySinh, boolean gioiTinh, boolean trangThai) ;
    KhachHang findByResetToken(String token);
    KhachHang findByEmailAndSdt(String email,String sdt);

    }
>>>>>>> nv_vinh
