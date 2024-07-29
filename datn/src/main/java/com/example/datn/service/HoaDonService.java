package com.example.datn.service;

import com.example.datn.entity.HoaDon;

import java.math.BigDecimal;
import java.util.List;


public interface HoaDonService {
    List<HoaDon> getAllHoaDon();

    List<HoaDon> getHoaDonChoXacNhan();

    List<HoaDon> getHoaDonDaXacNhan();

    List<HoaDon> getHoaDonChoGiaoHang();

    List<HoaDon> getHoaDonDangGiaoHang();

    List<HoaDon> getHoaDonDaGiaoHang();

    List<HoaDon> getHoaDonHoanThanh();

    List<HoaDon> getHoaDonHuy();

    HoaDon getHoaDonById(Long id);

    void save(Long gioHangId, Long khachHangId, String discountCode, BigDecimal discountAmount, BigDecimal totalAmount, Long thanhToan);

    void update(Long idHoaDon, Long idSanPhamChiTiet);

    void stepDown(Long hoaDonId, Long hoaDonChiTietId);

    void stepUp(Long hoaDonId, Long hoaDonChiTietId);

    void delete(Long hoaDonId, Long hoaDonChiTietId);

}
