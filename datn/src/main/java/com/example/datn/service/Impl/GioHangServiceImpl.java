package com.example.datn.service.Impl;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.GioHangChiTiet;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.GioHangChiTietRepository;
import com.example.datn.repository.GioHangRepository;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.service.GioHangService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private PhieuGiamGiaRepository pggRepository;


    @Override
    public GioHang getById(Long id) {
        return gioHangRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addToCart(Long gioHangId, Long sanPhamChiTietId) {
        Optional<GioHang> gioHangOpt = gioHangRepository.findById(gioHangId);
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(sanPhamChiTietId);

        if (gioHangOpt.isPresent() && sanPhamChiTietOpt.isPresent()) {
            GioHang gioHang = gioHangOpt.get();
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietOpt.get();

            Optional<GioHangChiTiet> existingItemOpt = gioHangChiTietRepository.findByGioHangAndSanPhamChiTiet(gioHang, sanPhamChiTiet);

            if (existingItemOpt.isPresent()) {
                GioHangChiTiet existingItem = existingItemOpt.get();
                existingItem.setSoLuong(existingItem.getSoLuong() + 1);
                gioHangChiTietRepository.save(existingItem);
            } else {
                GioHangChiTiet gioHangChiTiet = GioHangChiTiet.builder()
                        .gioHang(gioHang)
                        .sanPhamChiTiet(sanPhamChiTiet)
                        .soLuong(1)
                        .ngayTao(LocalDate.now())
                        .build();
                gioHangChiTietRepository.save(gioHangChiTiet);
            }
        } else {
            throw new IllegalArgumentException("GioHang or SanPhamChiTiet not found");
        }
    }

    @Override
    @Transactional
    public void stepDown(Long gioHangChiTietId, Long sanPhamChiTietId) {
        Optional<GioHangChiTiet> gioHangChiTietOpt = gioHangChiTietRepository.findById(gioHangChiTietId);
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(sanPhamChiTietId);

        if (gioHangChiTietOpt.isPresent() && sanPhamChiTietOpt.isPresent()) {
            GioHangChiTiet gioHangChiTiet = gioHangChiTietOpt.get();

            if (gioHangChiTiet.getSoLuong() > 1) {
                gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() - 1);
                gioHangChiTietRepository.save(gioHangChiTiet);
            } else {
                gioHangChiTietRepository.deleteById(gioHangChiTietId);
            }
        }
    }


    @Override
    @Transactional
    public void stepUp(Long gioHangChiTietId, Long sanPhamChiTietId) {
        Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId);
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(sanPhamChiTietId);
        if (gioHangChiTiet.isPresent() && sanPhamChiTietOpt.isPresent()) {
            gioHangChiTiet.get().setSoLuong(gioHangChiTiet.get().getSoLuong() + 1);
            gioHangChiTietRepository.save(gioHangChiTiet.get());
        }
    }

    @Override
    @Transactional
    public void delete(Long gioHangChiTietId) {
        gioHangChiTietRepository.findById(gioHangChiTietId).ifPresent(gioHangChiTiet ->
                gioHangChiTietRepository.deleteById(gioHangChiTietId)
        );
    }


    @Override
    public BigDecimal pgg(String discountCode, Long gioHangId) {
        Optional<GioHang> gioHangOptional = gioHangRepository.findById(gioHangId);
        PhieuGiamGia pgg = pggRepository.findByMaGiamGia(discountCode);

        if (gioHangOptional.isEmpty() || pgg == null) {
            return BigDecimal.ZERO;
        }

        GioHang gioHang = gioHangOptional.get();
        BigDecimal tongTien = BigDecimal.ZERO;

        List<GioHangChiTiet> gioHangChiTietList = gioHang.getGioHangChiTietList();
        for (GioHangChiTiet ghct : gioHangChiTietList) {
            BigDecimal donGia = ghct.getSanPhamChiTiet().getGiaBan();
            BigDecimal soLuong = new BigDecimal(ghct.getSoLuong());
            tongTien = tongTien.add(donGia.multiply(soLuong));
        }

        BigDecimal tienGiam = BigDecimal.ZERO;

        if (tongTien.compareTo(pgg.getGiaTriDonToiThieu()) >= 0) {
            if (pgg.isLoaiPhieu()) {
                BigDecimal discountPercent = new BigDecimal(pgg.getGiaTriGiam()).divide(new BigDecimal(100));
                tienGiam = tongTien.multiply(discountPercent);

                if (tienGiam.compareTo(pgg.getGiaTriGiamToiDa()) > 0) {
                    tienGiam = pgg.getGiaTriGiamToiDa();
                }
            } else {
                tienGiam = new BigDecimal(pgg.getGiaTriGiam());

                if (tienGiam.compareTo(pgg.getGiaTriGiamToiDa()) > 0) {
                    tienGiam = pgg.getGiaTriGiamToiDa();
                }
            }
        }

        return tienGiam;
    }

}
