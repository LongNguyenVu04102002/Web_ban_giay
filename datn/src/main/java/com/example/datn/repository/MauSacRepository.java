package com.example.datn.repository;

import com.example.datn.entity.CoGiay;
import com.example.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Long> {

    @Query("select spct.mauSac from SanPhamChiTiet spct where spct.sanPham.sanPhamId = :idSp")
    List<MauSac> getAllBySanPham(@Param("idSp") Long idSp);

    Optional<MauSac> findByTen(String ten);

    List<MauSac> findAllByTenAndMauSacIdNot(String ten, Long mauSacId);

    List<MauSac> findByTrangThai(boolean trangThai);
}
