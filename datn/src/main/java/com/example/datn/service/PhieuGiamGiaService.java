package com.example.datn.service;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PhieuGiamGiaService {

    Page<PhieuGiamGia> getAllPhieu(Pageable pageable);
    Page<PhieuGiamGia> filterPhieuGiamGia(Integer type, Pageable pageable);
    PhieuGiamGia getPhieuById(Long id);
    PhieuGiamGia savePhieuGiamGia(PhieuGiamGia phieuGiamGia);
    Optional<PhieuGiamGia> endPhieu(Long id);
    PhieuGiamGia update(PhieuGiamGia phieuGiamGia, Long id);
    PhieuGiamGia delete(Long id);
//    ResponseEntity<?> getAllPhieuGiamGia();
//    ResponseEntity<?> addPhieuGiamGia(PhieuGiamGia phieuGiamGia);
//    ResponseEntity<?> updatePhieuGiamGia(PhieuGiamGia phieuGiamGia, Long id);
//    ResponseEntity<?> deletePhieuGiamGia(Long id);
}
