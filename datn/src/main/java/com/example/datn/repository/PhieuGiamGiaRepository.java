package com.example.datn.repository;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {

<<<<<<< HEAD
    Page<PhieuGiamGia> findByLoaiPhieu(Long loaiPhieu, Pageable pageable);
    Page<PhieuGiamGia> findByTrangThai(String trangThai, Pageable pageable);
    Page<PhieuGiamGia> findByGiaTriDonToiThieuBetween(BigDecimal giaTriDonToiThieuMin, BigDecimal giaTriDonToiThieuMax, Pageable pageable);
    Page<PhieuGiamGia> findByNgayBatDauBetweenOrNgayKetThucBetween(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2, Pageable pageable);
    @Query("SELECT p FROM PhieuGiamGia p WHERE " +
            "p.maGiamGia LIKE %:query% OR " +
            "p.loaiPhieu LIKE %:query% OR " +
            "p.tienGiam LIKE %:query% OR " +
            "p.phanTramGiam LIKE %:query% OR " +
            "p.soLuongPhieu LIKE %:query% OR " +
            "p.giaTriDonToiThieu LIKE %:query% OR " +
            "p.giaTriGiamToiDa LIKE %:query% OR " +
            "p.ngayBatDau LIKE %:query% OR " +
            "p.ngayKetThuc LIKE %:query% OR " +
            "p.trangThai LIKE %:query%")
    Page<PhieuGiamGia> searchAllFields(@Param("query") String query, Pageable pageable);
=======
    PhieuGiamGia findByMaGiamGia(String maGiamGia);
>>>>>>> master

}
