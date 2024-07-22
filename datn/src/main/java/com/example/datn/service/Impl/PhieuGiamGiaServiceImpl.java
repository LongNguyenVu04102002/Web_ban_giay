package com.example.datn.service.impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        return phieuGiamGiaRepository.findAll(Sort
                .by(Sort.Direction.DESC, "phieuGiamGiaId"));
    }

    @Override
    public PhieuGiamGia getById(Long id) {
        return phieuGiamGiaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PhieuGiamGia phieuGiamGia) {

        if (phieuGiamGia.getMaGiamGia().isEmpty()) {
            phieuGiamGia.setMaGiamGia(generateVoucherCode());
            updateVoucherStatus(phieuGiamGia);
            phieuGiamGiaRepository.save(phieuGiamGia);
        } else {
            updateVoucherStatus(phieuGiamGia);
            phieuGiamGiaRepository.save(phieuGiamGia);
        }


    }

    @Override
    public void update(Long id) {
        Optional<PhieuGiamGia> phieuGiamGia = phieuGiamGiaRepository.findById(id);
        if (phieuGiamGia.isPresent()) {
            PhieuGiamGia pgg = phieuGiamGia.get();
            if (pgg.getTrangThai() == 1) {
                pgg.setTrangThai(3);
            } else if (pgg.getTrangThai() == 3) {
                pgg.setTrangThai(1);
            }
            phieuGiamGiaRepository.save(pgg);
        }
    }

    @Override
    public PhieuGiamGia tonggleTrangThaiGiamGia(Long id) {
        Optional<PhieuGiamGia> optionalGiamGia = phieuGiamGiaRepository.findById(id);
        if (optionalGiamGia.isPresent()) {
            PhieuGiamGia giamGia = optionalGiamGia.get();
            int currentTrangThai = giamGia.getTrangThai(); // Giả sử bạn có phương thức getTrangThai()

            switch (currentTrangThai) {
                case 1:
                    giamGia.setTrangThai(3);
                    break;
                // Bạn có thể thêm các case khác nếu cần
                default:
                    // Giữ nguyên trạng thái nếu không phải 2
                    break;
            }

            return phieuGiamGiaRepository.save(giamGia);
        }
        return null;
    }


    private String generateVoucherCode() {
        String prefix = "XBOY";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Generate 5 random characters
        for (int i = 0; i < 5; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return prefix + sb.toString();
    }

    private void updateVoucherStatus(PhieuGiamGia phieuGiamGia) {
        LocalDate currentDate = LocalDate.now();

        if (phieuGiamGia.getNgayKetThuc() != null && phieuGiamGia.getNgayKetThuc().isBefore(currentDate)) {
            phieuGiamGia.setTrangThai(3);
        } else if (phieuGiamGia.getNgayBatDau() != null && phieuGiamGia.getNgayBatDau().isBefore(currentDate)) {
            phieuGiamGia.setTrangThai(1);
        } else {
            phieuGiamGia.setTrangThai(2);
        }
    }
}
