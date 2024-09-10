package com.example.datn.repository;

import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    List<SanPhamChiTiet> findBySanPham(SanPham sanPham);

    @Query("SELECT p.giaBan FROM SanPhamChiTiet p WHERE p.sanPham.sanPhamId = :sanPhamId  AND p.kichThuoc.kichThuocId = :sizeId AND p.mauSac.mauSacId = :colorId")
    BigDecimal findPriceBySizeAndColor(@Param("sanPhamId") Long sanPhamId, @Param("sizeId") Long sizeId, @Param("colorId") Long colorId);

    @Query("SELECT p.soLuong FROM SanPhamChiTiet p WHERE p.sanPham.sanPhamId = :sanPhamId  AND p.kichThuoc.kichThuocId = :sizeId AND p.mauSac.mauSacId = :colorId")
    int findQuantityBySizeAndColor(@Param("sanPhamId") Long sanPhamId, @Param("sizeId") Long sizeId, @Param("colorId") Long colorId);

    @Query("SELECT p FROM SanPhamChiTiet p WHERE p.sanPham.ten like :tenSanPham  AND p.kichThuoc.ten like :sizeId AND p.mauSac.ten like :colorId")
    SanPhamChiTiet findByNameSizeAndColor(String tenSanPham, String sizeId, String colorId);

    @Query("SELECT p FROM SanPhamChiTiet p WHERE p.sanPham.ten = :tenSanPham  AND p.kichThuoc.ten = :tenKichThuoc AND p.mauSac.ten = :tenMauSac")
    SanPhamChiTiet findByName(String tenSanPham, String tenKichThuoc, String tenMauSac);

    List<SanPhamChiTiet> findBySanPham_SanPhamIdAndKichThuoc_KichThuocIdAndMauSac_MauSacId(Long sanPhamId, Long kichThuocId, Long mauSacId);

    List<SanPhamChiTiet> findAllBySanPham_SanPhamIdAndMauSac_MauSacIdOrderBySanPhamChiTietId(Long idSp, Long idMs);

    @Query("SELECT sp FROM SanPhamChiTiet sp WHERE sp.sanPham.sanPhamId = :sanPhamId AND sp.kichThuoc.kichThuocId = :kichThuocId AND sp.mauSac.mauSacId = :mauSacId AND sp.sanPhamChiTietId <> :sanPhamChiTietId")
    List<SanPhamChiTiet> findDuplicateExceptCurrent(Long sanPhamId, Long kichThuocId, Long mauSacId, Long sanPhamChiTietId);

    List<SanPhamChiTiet> findAllByTrangThaiTrue();

}
