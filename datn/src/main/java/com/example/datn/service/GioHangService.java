package com.example.datn.service;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.GioHangChiTiet;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.GioHangChiTietRepository;
import com.example.datn.repository.GioHangRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository; // Thêm repository để truy cập sản phẩm chi tiết

    @Autowired
    private KhachHangRepository khachHangRepository; // Thêm repository để truy cập khách hàng

    public GioHang addProductToCart(Long khachHangId, Long sanPhamChiTietId, Integer soLuong) {
        // Tìm khách hàng từ cơ sở dữ liệu
        Optional<KhachHang> khachHangOpt = khachHangRepository.findById(khachHangId);
        if (!khachHangOpt.isPresent()) {
            throw new RuntimeException("Khách hàng không tồn tại!");
        }
        KhachHang khachHang = khachHangOpt.get();

        // Tìm giỏ hàng của khách hàng
        GioHang gioHang = gioHangRepository.findByKhachHang_KhachHangId(khachHangId);
        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setKhachHang(khachHang);
            gioHang.setNgayTao(LocalDate.now());
            gioHang.setTrangThai(true);
        }

        // Tìm sản phẩm chi tiết từ cơ sở dữ liệu
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(sanPhamChiTietId);
        if (!sanPhamChiTietOpt.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại!");
        }

        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setSanPhamChiTiet(sanPhamChiTietOpt.get());
        gioHangChiTiet.setSoLuong(soLuong);
        gioHangChiTiet.setNgayTao(LocalDate.now());
        gioHangChiTiet.setGioHang(gioHang);

        gioHang.getGioHangChiTietList().add(gioHangChiTiet);
        gioHangRepository.save(gioHang);

        return gioHang;
    }

    public GioHang findCartByCustomerId(Long khachHangId) {
        return gioHangRepository.findByKhachHang_KhachHangId(khachHangId);
    }
}
