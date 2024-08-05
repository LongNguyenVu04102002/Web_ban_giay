package com.example.datn.service;

import com.example.datn.dto.CartItem;
import com.example.datn.entity.HoaDon;
<<<<<<< HEAD
import com.example.datn.entity.KhachHang;
=======
>>>>>>> parent of f4e9d10 (update)
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
<<<<<<< HEAD
    public String saveHoaDonOnline(ThanhToanResponse thanhToanResponse, List<CartItem> cartItems, KhachHang khachHang) ;
=======
    String saveHoaDonOnline(ThanhToanResponse thanhToanResponse, List<CartItem> cartItems);
>>>>>>> parent of f4e9d10 (update)

    void update(Long idHoaDon, Long idSanPhamChiTiet);

    void stepDown(Long hoaDonId, Long hoaDonChiTietId);

    void stepUp(Long hoaDonId, Long hoaDonChiTietId);

    void delete(Long hoaDonId, Long hoaDonChiTietId);

}
