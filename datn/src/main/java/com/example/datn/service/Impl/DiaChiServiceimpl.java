package com.example.datn.service.Impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiServiceimpl implements DiaChiService {
    @Autowired
    private DiaChiRepository diaChiRepository;

    @Override
    public List<DiaChi> findByKhachHangId(Long khachHangId) {
        return diaChiRepository.findByKhachHang_KhachHangId(khachHangId);
    }
}
