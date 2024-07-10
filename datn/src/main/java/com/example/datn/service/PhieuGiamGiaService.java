package com.example.datn.service;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface PhieuGiamGiaService {

    Page<PhieuGiamGia> getAllPhieu(Pageable pageable);

    PhieuGiamGia getPhieuById(Long id);

    PhieuGiamGia savePhieuGiamGia(PhieuGiamGia phieuGiamGia);

    Optional<PhieuGiamGia> endPhieu(Long id);

    PhieuGiamGia update(PhieuGiamGia phieuGiamGia, Long id);

    PhieuGiamGia delete(Long id);

    Page<PhieuGiamGia> searchLoaiPhieu(Long loaiPhieu, Pageable pageable);

    Page<PhieuGiamGia> searchTrangThai(String trangThai, Pageable pageable);

    Page<PhieuGiamGia> searchDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);


    Page<PhieuGiamGia> searchDonToiThieu(BigDecimal donToiThieuMin, BigDecimal donToiThieuMax, Pageable pageable);

//    Page<PhieuGiamGia> searchAllFields(String query, Pageable pageable);

}
