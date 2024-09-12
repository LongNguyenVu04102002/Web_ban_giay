package com.example.datn.repository;

import com.example.datn.dto.SanPhamHomeDTO;
import com.example.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    List<SanPham> findAllByThuongHieu_ThuongHieuId(Long id);

    List<SanPham> findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(String ten, Long chatLieuId, Long coGiayId, Long dayGiayId, Long deGiayId, Long lotGiayId, Long muiGiayId, Long thuongHieuId);

    @Query("SELECT new com.example.datn.dto.SanPhamHomeDTO(sp.sanPhamId, sp.ten, " +
            "MIN(spct.giaBan), " +
            "CONCAT('data:image/jpeg;base64,', TO_BASE64((SELECT ha.dataImg FROM HinhAnh ha WHERE ha.sanPhamChiTiet.sanPham = sp ORDER BY ha.hinhAnhId ASC LIMIT 1))) " +
            ") " +
            "FROM SanPham sp " +
            "JOIN sp.sanPhamChiTietList spct " +
            "WHERE sp.trangThai = false " +
            "GROUP BY sp.sanPhamId, sp.ten " +
            "HAVING COUNT(spct) > 0")
    Page<SanPhamHomeDTO> getSanPhamForHomePage(Pageable pageable);


    @Query("SELECT new com.example.datn.dto.SanPhamHomeDTO(sp.sanPhamId, sp.ten, MIN(spct.giaBan), " +
            "CONCAT('data:image/jpeg;base64,', TO_BASE64((SELECT ha.dataImg FROM HinhAnh ha WHERE ha.sanPhamChiTiet.sanPham = sp ORDER BY ha.hinhAnhId ASC LIMIT 1))) " +
            ") " +

            "FROM SanPham sp " +
            "JOIN sp.sanPhamChiTietList spct " +
            "WHERE sp.trangThai = true " +
            "AND (:thuongHieuId IS NULL OR sp.thuongHieu.thuongHieuId = :thuongHieuId) " +
            "AND (:kichThuocId IS NULL OR spct.kichThuoc.kichThuocId = :kichThuocId) " +
            "AND (:mauSacId IS NULL OR spct.mauSac.mauSacId = :mauSacId) " +
            "AND (:keyword IS NULL OR sp.ten LIKE %:keyword%) " +
            "GROUP BY sp.sanPhamId, sp.ten " +
            "HAVING COUNT(spct) > 0")
    Page<SanPhamHomeDTO> findSanPhamForShopPage(
            @Param("thuongHieuId") Long thuongHieuId,
            @Param("kichThuocId") Long kichThuocId,
            @Param("mauSacId") Long mauSacId,
            @Param("keyword") String keyword,
            Pageable pageable);


    List<SanPham> findAllByTrangThaiTrue();
}


