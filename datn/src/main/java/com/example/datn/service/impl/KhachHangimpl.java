package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangimpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    DiaChiRepository diaChiRepository;
    @Override
    public ResponseEntity<?> getAllKhachHang() {
        List<KhachHang> khachHangList = khachHangRepository.findAll();
        return ResponseEntity.ok(khachHangList);
    }

    @Override
    public ResponseEntity<?> addKhachHang(KhachHang khachHang) {
        KhachHang savedKhachHang = khachHangRepository.save(khachHang);

        return ResponseEntity.ok(savedKhachHang);
    }

    @Override
    public ResponseEntity<?> updateKhachHang(KhachHang khachHang, Long id) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(id);
        if (optionalKhachHang.isPresent()) {
            KhachHang existingKhachHang = optionalKhachHang.get();
            existingKhachHang.setHoTen(khachHang.getHoTen());
            existingKhachHang.setGioiTinh(khachHang.isGioiTinh());
            existingKhachHang.setNgaySinh(khachHang.getNgaySinh());
            existingKhachHang.setMatKhau(khachHang.getMatKhau());
            existingKhachHang.setTrangThai(khachHang.isTrangThai());
            existingKhachHang.setDiaChiList(khachHang.getDiaChiList());
            existingKhachHang.setEmail(khachHang.getEmail());
            existingKhachHang.setSdt(khachHang.getSdt());
            KhachHang updatedKhachHang = khachHangRepository.save(existingKhachHang);
            return ResponseEntity.ok(updatedKhachHang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Override
    public ResponseEntity<?> deleteKhachHang(Long id) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(id);
        if (optionalKhachHang.isPresent()) {
            khachHangRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    }



