package com.example.datn.service;

import com.example.datn.entity.TaiKhoan;

import java.util.Optional;

public interface TaiKhoanService {
    Optional<TaiKhoan> findByEmail(String email);

    Boolean existsByEmail(String email);

    TaiKhoan save(TaiKhoan taiKhoan);
    
}
