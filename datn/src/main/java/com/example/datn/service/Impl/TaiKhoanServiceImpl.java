package com.example.datn.service.Impl;

import com.example.datn.entity.TaiKhoan;
import com.example.datn.repository.TaiKhoanRepository;
import com.example.datn.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public Optional<TaiKhoan> findByEmail(String email) {
        return taiKhoanRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return taiKhoanRepository.existsByEmail(email);
    }

    @Override
    public TaiKhoan save(TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }

}
