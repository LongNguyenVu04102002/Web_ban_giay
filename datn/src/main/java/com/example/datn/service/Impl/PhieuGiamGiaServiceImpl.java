package com.example.datn.service.Impl;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Override
    public ResponseEntity<?> getPhieuGiamGiaByMa(String maGiamGia) {
        return ResponseEntity.ok(phieuGiamGiaRepository.findByMaGiamGia(maGiamGia));
    }

    @Override
    public List<PhieuGiamGia> getAll() {
        return phieuGiamGiaRepository.findAll();
    }

    @Override
    public PhieuGiamGia getById(Long id) {
        return phieuGiamGiaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PhieuGiamGia phieuGiamGia) {
        phieuGiamGia.setMaGiamGia(randomMaGiamGia(10));
        LocalDate currentDate = LocalDate.now();


        if (phieuGiamGia.getNgayKetThuc() != null && phieuGiamGia.getNgayKetThuc().isBefore(currentDate)) {
            phieuGiamGia.setTrangThai(3);
        } else if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayBatDau().isBefore(currentDate)) {
            phieuGiamGia.setTrangThai(1);
        } else {
            phieuGiamGia.setTrangThai(2);
        }

        phieuGiamGiaRepository.save(phieuGiamGia);
    }

    public static String randomMaGiamGia(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

}
