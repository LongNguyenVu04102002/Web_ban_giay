package com.example.datn.service;

import com.example.datn.entity.KhachHang;
import com.example.datn.model.request.SignupRequest;
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

    void register(SignupRequest signupRequest);

    KhachHang findByResetToken(String token);

    KhachHang findByEmailAndSdt(String email, String sdt);

}
