package com.example.datn.service;

import com.example.datn.controller.HoaDonController;
import com.example.datn.dto.CartItem;
import com.example.datn.entity.HoaDon;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;

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

    void saveHoaDonTaiQuay(Long gioHangId, Long khachHangId, String discountCode, BigDecimal discountAmount, BigDecimal totalAmount, Long thanhToan);
    String saveHoaDonOnline(PhieuGiamGiaResponse phieuGiamGiaResponse, ThanhToanResponse thanhToanResponse, List<CartItem> cartItems);

    void update(Long idHoaDon, Long idSanPhamChiTiet);

    void stepDown(Long hoaDonId, Long hoaDonChiTietId);

    void stepUp(Long hoaDonId, Long hoaDonChiTietId);

    void updateThongTinGiaoHang(HoaDon hoaDon);

    void delete(Long hoaDonId, Long hoaDonChiTietId);

}
