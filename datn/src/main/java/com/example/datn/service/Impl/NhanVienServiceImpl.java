package com.example.datn.service.Impl;

import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if(nhanVien.getNhanVienId() == null){
            nhanVien.setTrangThai(true);
            long count = nhanVienRepository.count();
            String newMaNhanVien = "NV" + (count + 1);
            nhanVien.setMaNhanVien(newMaNhanVien);
        }
        nhanVien.setRole("STAFF");
        nhanVien.setPassword(passwordEncoder.encode(nhanVien.getSdt()));
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public boolean existsBySdt(String sdt) {
        return nhanVienRepository.findBySdt(sdt).isPresent();
    }

    @Override
    public NhanVien existsByEmail(String email) {
        return nhanVienRepository.findByEmail(email);
    }

    @Override
    public NhanVien toggleTrangThai(Long nhanVienId) {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(nhanVienId);
        if (optionalNhanVien.isPresent()) {
            NhanVien nhanVien = optionalNhanVien.get();
            nhanVien.setTrangThai(!nhanVien.isTrangThai());
            return nhanVienRepository.save(nhanVien);
        }
        return null;
    }

}
