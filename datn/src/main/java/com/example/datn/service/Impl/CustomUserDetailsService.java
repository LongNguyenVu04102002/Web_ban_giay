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
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting to load user: " + email);

        NhanVien nhanVien = nhanVienRepository.getAllByEmail(email);
        if (nhanVien != null) {
            System.out.println("Found NhanVien: " + nhanVien.getEmail());
            System.out.println("Found role: " + nhanVien.getRole());
            System.out.println("Stored password: " + nhanVien.getMatKhau());
            return new User(nhanVien.getEmail(), nhanVien.getMatKhau(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + nhanVien.getRole().toUpperCase())));
        }

        KhachHang khachHang = khachHangRepository.findByEmail(email);
        if (khachHang != null) {
            System.out.println("Found KhachHang: " + khachHang.getEmail());
            System.out.println("Stored password: " + khachHang.getMatKhau());
            return new User(khachHang.getEmail(), khachHang.getMatKhau(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_KHACHHANG")));
        }

        System.out.println("User not found: " + email);
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}