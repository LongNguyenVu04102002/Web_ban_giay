package com.example.datn.service;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.http.ResponseEntity;

public interface PhieuGiamGiaService {

    ResponseEntity<?> getAllPhieuGiamGia();
    ResponseEntity<?> addPhieuGiamGia(PhieuGiamGia phieuGiamGia);
    ResponseEntity<?> updatePhieuGiamGia(PhieuGiamGia phieuGiamGia, Long id);
    ResponseEntity<?> deletePhieuGiamGia(Long id);
}
