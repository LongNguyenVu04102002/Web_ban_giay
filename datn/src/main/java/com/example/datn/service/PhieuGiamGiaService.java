package com.example.datn.service;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.entity.ThuongHieu;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhieuGiamGiaService {

    ResponseEntity<?> getPhieuGiamGiaByMa(String ma);

    List<PhieuGiamGia> getAll();

    PhieuGiamGia getById(Long id);

    void save(PhieuGiamGia phieuGiamGia);

<<<<<<< HEAD
    PhieuGiamGia update(PhieuGiamGia phieuGiamGia, Long id);

    PhieuGiamGia delete(Long id);

    Page<PhieuGiamGia> searchLoaiPhieu(Long loaiPhieu, Pageable pageable);

    Page<PhieuGiamGia> searchTrangThai(String trangThai, Pageable pageable);

    Page<PhieuGiamGia> searchDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);


    Page<PhieuGiamGia> searchDonToiThieu(BigDecimal donToiThieuMin, BigDecimal donToiThieuMax, Pageable pageable);

//    Page<PhieuGiamGia> searchAllFields(String query, Pageable pageable);

=======
>>>>>>> master
}
