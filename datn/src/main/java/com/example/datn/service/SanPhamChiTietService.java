package com.example.datn.service;

import com.example.datn.entity.SanPhamChiTiet;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface SanPhamChiTietService {

    ResponseEntity<?> getAllSanPhamChiTiet();

    ResponseEntity<?> getSanPhamChiTietById(Long id);

    List<SanPhamChiTiet> getAll();

    SanPhamChiTiet getById(Long id);

    boolean findBySanPham_SanPhamIdAndKichThuoc_KichThuocIdAndMauSac_MauSacId(Long sanPhamId, Long kichThuocId, Long mauSacId);

    void add(SanPhamChiTiet sanPhamChiTiet);

    void saveOfUpdate(SanPhamChiTiet sanPhamChiTiet);

    void update(Long id);

    BigDecimal getPrice(Long sanPhamId, Long sizeId, Long colorId);

    boolean isDuplicate(Long sanPhamId, Long kichThuocId, Long mauSacId, Long sanPhamChiTietId);

}
