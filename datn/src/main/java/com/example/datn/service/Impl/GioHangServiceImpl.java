package com.example.datn.service.Impl;

import com.example.datn.dto.TabDataDTO;
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
import java.math.RoundingMode;
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
    private PhieuGiamGiaRepository phieuGiamGiaRepository;


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
            updateCart(gioHang);
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

            updateCart(gioHangChiTiet.getGioHang());
        }

    }

    @Override
    @Transactional
    public void stepUp(Long gioHangChiTietId, Long sanPhamChiTietId) {
        Optional<GioHangChiTiet> gioHangChiTietOpt = gioHangChiTietRepository.findById(gioHangChiTietId);
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(sanPhamChiTietId);

        if (gioHangChiTietOpt.isPresent() && sanPhamChiTietOpt.isPresent()) {
            GioHangChiTiet gioHangChiTiet = gioHangChiTietOpt.get();
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + 1);
            gioHangChiTietRepository.save(gioHangChiTiet);

            updateCart(gioHangChiTiet.getGioHang());
        }
    }

    @Override
    public void delete(Long gioHangChiTietId) {
        Optional<GioHangChiTiet> gioHangChiTietOpt = gioHangChiTietRepository.findById(gioHangChiTietId);

        if (gioHangChiTietOpt.isPresent()) {
            GioHangChiTiet gioHangChiTiet = gioHangChiTietOpt.get();
            gioHangChiTietRepository.deleteById(gioHangChiTietId);

            updateCart(gioHangChiTiet.getGioHang());
        }

    }

    public TabDataDTO updateCart(GioHang gioHang) {
        BigDecimal totalCartValue = gioHang.getGioHangChiTietList().stream()
                .map(item -> {
                    BigDecimal quantity = BigDecimal.valueOf(item.getSoLuong());
                    BigDecimal price = item.getSanPhamChiTiet().getGiaBan();
                    return quantity.multiply(price);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<PhieuGiamGia> phieuGiamGiaList = phieuGiamGiaRepository.findAll();

        PhieuGiamGia bestDiscount = null;
        BigDecimal bestDiscountAmount = BigDecimal.ZERO;

        // Tim ma giam gia tot nhat
        for (PhieuGiamGia pgg : phieuGiamGiaList) {
            if (pgg.getTrangThai() == 1 && totalCartValue.compareTo(pgg.getGiaTriDonToiThieu()) >= 0) {
                BigDecimal discountAmount = BigDecimal.ZERO;

                if (pgg.isLoaiPhieu()) {
                    discountAmount = totalCartValue.multiply(BigDecimal.valueOf(pgg.getGiaTriGiam())).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                } else {
                    discountAmount = BigDecimal.valueOf(pgg.getGiaTriGiam());
                }

                if (discountAmount.compareTo(pgg.getGiaTriGiamToiDa()) > 0) {
                    discountAmount = pgg.getGiaTriGiamToiDa();
                }

                if (discountAmount.compareTo(bestDiscountAmount) > 0) {
                    bestDiscount = pgg;
                    bestDiscountAmount = discountAmount;
                }
            }
        }

        BigDecimal muaThemDeGiam = BigDecimal.ZERO;
        PhieuGiamGia betterDiscount = null;

        for (PhieuGiamGia pgg : phieuGiamGiaList) {
            if (pgg.getTrangThai() == 1 && totalCartValue.compareTo(pgg.getGiaTriDonToiThieu()) < 0) {
                BigDecimal additionalAmount = pgg.getGiaTriDonToiThieu().subtract(totalCartValue);
                if (betterDiscount == null || additionalAmount.compareTo(muaThemDeGiam) < 0) {
                    muaThemDeGiam = additionalAmount;
                    betterDiscount = pgg;
                }
            }
        }

        if (bestDiscount != null) {
            gioHang.setTienGiam(bestDiscountAmount);
            gioHangRepository.save(gioHang);

            if (betterDiscount != null && bestDiscount.getGiaTriDonToiThieu().compareTo(betterDiscount.getGiaTriDonToiThieu()) < 0) {
                muaThemDeGiam = betterDiscount.getGiaTriDonToiThieu().subtract(totalCartValue);
            }

            return TabDataDTO.builder()
                    .id(gioHang.getGioHangId())
                    .name("Giỏ hàng " + gioHang.getGioHangId())
                    .phieuGiamGiaId(bestDiscount.getPhieuGiamGiaId())
                    .maPhieu(bestDiscount.getMaGiamGia())
                    .tienGiam(bestDiscountAmount)
                    .muaThemDeGiam(muaThemDeGiam)
                    .build();
        } else {
            gioHang.setTienGiam(BigDecimal.ZERO);
            gioHangRepository.save(gioHang);

            return TabDataDTO.builder()
                    .id(gioHang.getGioHangId())
                    .name("Giỏ hàng " + gioHang.getGioHangId())
                    .phieuGiamGiaId(null)
                    .maPhieu(null)
                    .tienGiam(BigDecimal.ZERO)
                    .muaThemDeGiam(muaThemDeGiam)
                    .build();
        }
    }

    @Override
    public void save(GioHang gioHang) {
        gioHangRepository.save(gioHang);
    }

}
