package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.model.request.SignupRequest;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll((Sort.by(Sort.Direction.DESC, "khachHangId")));
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }


    @Override
    public KhachHang save(KhachHang khachHang) {
        khachHang.setPassword(passwordEncoder.encode(khachHang.getSdt()));
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang toggleTrangThai(Long khachHangId) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(khachHangId);
        if (optionalKhachHang.isPresent()) {
            KhachHang khachHang = optionalKhachHang.get();
            khachHang.setTrangThai(!khachHang.isTrangThai());
            return khachHangRepository.save(khachHang);
        }
        return null;
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public boolean isSdtExist(String sdt) {
        return khachHangRepository.existsBySdt(sdt);
    }

    @Override
    public boolean isEmailExist(String email) {
        return khachHangRepository.existsByEmail(email);
    }

    public boolean isPhoneNumberDuplicate(String sdt, Long excludeId) {
        return khachHangRepository.existsBySdtAndKhachHangIdNot(sdt, excludeId);
    }

    public boolean isEmailDuplicate(String email, Long excludeId) {
        return khachHangRepository.existsByEmailAndKhachHangIdNot(email, excludeId);
    }

    public void register(SignupRequest signupRequest) {
        KhachHang khachHang = KhachHang.builder()
                .email(signupRequest.getEmail())
                .hoTen(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .trangThai(true)
                .gioiTinh(true)
                .build();
        khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang findByResetToken(String token) {
        return khachHangRepository.findByResetToken(token);
    }

    @Override
    public KhachHang findByEmailAndSdt(String email, String sdt) {
        return khachHangRepository.findByEmailAndSdt(email, sdt);
    }

    @Override
    public KhachHang login(String email, String password) {
        KhachHang khachHang = khachHangRepository.findKhachHangByEmailAndPassword(email, password);
        if(khachHang != null) {
            if (khachHang.isTrangThai()) {
                return null;
            }
            return khachHang;
        }
        return null;
    }

}