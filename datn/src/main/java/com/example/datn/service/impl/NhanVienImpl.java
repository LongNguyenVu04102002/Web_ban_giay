package com.example.datn.service.impl;

import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NhanVienImpl implements NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Override
    public ResponseEntity<?> getAllNhanVien() {
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        return ResponseEntity.ok(nhanVienList);
    }

    @Override
    public ResponseEntity<?> addNhanVien(NhanVien nhanVien) {
        NhanVien saveNhanVien = nhanVienRepository.save(nhanVien);
        return ResponseEntity.ok(saveNhanVien);
    }

    @Override
    public ResponseEntity<?> updateNhanVien(NhanVien nhanVien, Long id) {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()){
            NhanVien existingNhanVien = optionalNhanVien.get();
            existingNhanVien.setHoTen(nhanVien.getHoTen());
            existingNhanVien.setGioiTinh(nhanVien.isGioiTinh());
            existingNhanVien.setNgaySinh(nhanVien.getNgaySinh());
            existingNhanVien.setSdt(nhanVien.getSdt());
            existingNhanVien.setEmail(nhanVien.getEmail());
            existingNhanVien.setMatKhau(nhanVien.getMatKhau());
            existingNhanVien.setTrangThai(nhanVien.isTrangThai());
            existingNhanVien.setRole(nhanVien.getRole());
            return  ResponseEntity.ok(updateNhanVien(nhanVien,id));

        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<?> deleteNhanVien(Long id) {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()){
            nhanVienRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
