package com.example.datn.service.Impl;

import com.example.datn.dto.MyUserDetail;
import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        if (nhanVien == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + nhanVien.getRole());
        MyUserDetail myUserDetail = new MyUserDetail(
                email,
                nhanVien.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singletonList(authority)
        );
        myUserDetail.setId(nhanVien.getNhanVienId());
        myUserDetail.setFullName(nhanVien.getHoTen());
        myUserDetail.setEmail(nhanVien.getEmail());

        return myUserDetail;
    }

}
