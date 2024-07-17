package com.example.datn.service.impl;

import com.example.datn.entity.HinhThucThanhToan;
import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.entity.PhuongThucThanhToan;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.TimeLine;
import com.example.datn.model.response.CartResponse;
import com.example.datn.model.response.HoaDonResponse;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.repository.*;
import com.example.datn.service.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HinhThucThanhToanRepository hinhThucThanhToanRepository;

    @Autowired
    private PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.getAllHoaDon() ;
    }

    @Override
    public List<HoaDon> getHoaDonHuy() {
        return hoaDonRepository.getHoaDonHuy();
    }

    @Override
    public List<HoaDon> getHoaDonChoXacNhan() {
        return hoaDonRepository.getHoaDonChoXacNhan();
    }

    @Override
    public List<HoaDon> getHoaDonDaXacNhan() {
        return hoaDonRepository.getHoaDonDaXacNhan();
    }

    @Override
    public List<HoaDon> getHoaDonChoGiaoHang() {
        return hoaDonRepository.getHoaDonChoGiaoHang();
    }

    @Override
    public List<HoaDon> getHoaDonDangGiaoHang() {
        return hoaDonRepository.getHoaDonDangGiaoHang();
    }

    @Override
    public List<HoaDon> getHoaDonDaGiaoHang() {
        return hoaDonRepository.getHoaDonDaGiaoHang();
    }

    @Override
    public List<HoaDon> getHoaDonHoanThanh() {
        return hoaDonRepository.getHoaDonHoanThanh();
    }

    @Override
    public HoaDon getHoaDonById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public ResponseEntity<?> addHoaDon(HoaDonResponse hoaDonResponse) {
        try {
            HoaDon hoaDon = new HoaDon();
            ThanhToanResponse thanhToanResponse = hoaDonResponse.getThanhToanResponse();
            if (thanhToanResponse == null) {
                return ResponseEntity.badRequest().body("KhachHang is null");
            }

            hoaDon.setTenNguoiNhan(thanhToanResponse.getHoTen());
            hoaDon.setEmail(thanhToanResponse.getEmail());
            hoaDon.setSdtNhan(thanhToanResponse.getSdt());
            hoaDon.setDiaChiNhan(thanhToanResponse.getDiaChi());
            hoaDon.setLoaiHoaDon(false);
            hoaDon.setThanhToan(false);
            hoaDon.setTrangThai(1);
            hoaDon.setPhiShip(thanhToanResponse.getTienShip());
            hoaDon.setMaVanDon(thanhToanResponse.getMaVanDon());
            hoaDon.setTongTien(thanhToanResponse.getTongTien());

            // Save PhieuGiamGia
            PhieuGiamGiaResponse phieuGiamGiaResponse = hoaDonResponse.getPhieuGiamGiaResponse();
            if (phieuGiamGiaResponse != null) {
                PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(phieuGiamGiaResponse.getPhieuGiamGiaId()).orElse(null);
                if (phieuGiamGia == null) {
                    return ResponseEntity.badRequest().body("PhieuGiamGia not found");
                }
                hoaDon.setPhieuGiamGia(phieuGiamGia);
                hoaDon.setTienGiam(phieuGiamGiaResponse.getTienGiam());
            }

            // Save HoaDon
            hoaDonRepository.save(hoaDon);

            //Save PhuongThucThanhToan
            if(thanhToanResponse.isHinhThuc()){
                HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan();
                PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanRepository.findById(1L).orElse(null);
                hinhThucThanhToan.setPhuongThucThanhToan(phuongThucThanhToan);
                hinhThucThanhToan.setHoaDon(hoaDon);
                hinhThucThanhToanRepository.save(hinhThucThanhToan);
            }

            // Save HoaDonChiTiet
            for (CartResponse response : hoaDonResponse.getCartResponseList()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(response.getSanPhamChiTietId()).orElse(null);
                if (sanPhamChiTiet == null) {
                    return ResponseEntity.badRequest().body("SanPhamChiTiet not found");
                }
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
                hoaDonChiTiet.setSoLuong(response.getSoLuong());
                hoaDonChiTiet.setTrangThai(1);
                hoaDonChiTiet.setDonGia(BigDecimal.valueOf((long) response.getSoLuong() * response.getGia()));
                hoaDonChiTietRepository.save(hoaDonChiTiet);
            }

            // Save TimeLine
            TimeLine timeLine = new TimeLine();
            timeLine.setNgayTao(LocalDate.now());
            timeLine.setHoaDon(hoaDon);
            timeLine.setTrangThai(1);
            timeLineRepository.save(timeLine);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(hoaDonRepository.getAllHoaDon());
    }

}
