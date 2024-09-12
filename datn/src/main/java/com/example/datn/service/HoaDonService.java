package com.example.datn.service;

import com.example.datn.dto.CartItem;
import com.example.datn.entity.HoaDon;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;

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

    List<HoaDon> getHoaDonKhachHang(Long idKhachHang);

    HoaDon getHoaDonById(Long id);

    boolean saveHoaDonTaiQuay(Long gioHangId, Long khachHangId, String discountCode, ThanhToanResponse thanhToanResponse);

    String saveHoaDonOnline(Long khachHangId, PhieuGiamGiaResponse phieuGiamGiaResponse, ThanhToanResponse thanhToanResponse, List<CartItem> cartItems);

    void update(Long idHoaDon, Long idSanPhamChiTiet);

    void stepDown(Long hoaDonId, Long hoaDonChiTietId);

    void stepUp(Long hoaDonId, Long hoaDonChiTietId);

    void updateThongTinGiaoHang(HoaDon hoaDon);

    void delete(Long hoaDonId, Long hoaDonChiTietId);

}
