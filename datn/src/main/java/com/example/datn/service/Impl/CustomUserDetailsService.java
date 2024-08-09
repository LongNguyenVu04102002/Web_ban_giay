package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First try to load as KhachHang
        KhachHang khachHang = khachHangRepository.findByEmail(email);
        if (khachHang != null) {
            return khachHang;
        }

        // If not found, try to load as NhanVien
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        if (nhanVien != null) {
            return nhanVien;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}