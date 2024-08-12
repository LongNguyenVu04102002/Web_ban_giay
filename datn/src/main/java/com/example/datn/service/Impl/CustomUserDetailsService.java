package com.example.datn.service.Impl;

import com.example.datn.dto.MyUserDetail;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First try to load as KhachHang
        MyUserDetail myUserDetail = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        KhachHang khachHang = khachHangRepository.findByEmail(email);
        if (khachHang != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            myUserDetail = new MyUserDetail(email, khachHang.getPassword(), true, true, true, true, authorities);
            myUserDetail.setId(khachHang.getKhachHangId());
            myUserDetail.setFullName(khachHang.getHoTen());
            myUserDetail.setEmail(khachHang.getEmail());
        }

        // If not found, try to load as NhanVien
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        if (nhanVien != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+nhanVien.getRole()));
            myUserDetail = new MyUserDetail(email, nhanVien.getPassword(), true, true, true, true, authorities);
            myUserDetail.setId(nhanVien.getNhanVienId());
            myUserDetail.setFullName(nhanVien.getHoTen());
            myUserDetail.setEmail(nhanVien.getEmail());
        }

        if(Objects.isNull(myUserDetail))
            throw new UsernameNotFoundException("User not found with email: " + email);

        return myUserDetail;
    }
}