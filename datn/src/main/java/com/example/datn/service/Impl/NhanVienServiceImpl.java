package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien getById(Long id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    @Override
    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public void updateTrangThai(Long id) {
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(id);
        if(nhanVien.isPresent()) {
            NhanVien nv = nhanVien.get();
            nv.setTrangThai(!nv.isTrangThai());
            nhanVienRepository.save(nv);
        }
    }

}
