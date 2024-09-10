package com.example.datn.service.Impl;

import com.example.datn.dto.CartItem;
import com.example.datn.dto.MyUserDetail;
import com.example.datn.entity.GioHang;
import com.example.datn.entity.GioHangChiTiet;
import com.example.datn.entity.HinhThucThanhToan;
import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.entity.PhuongThucThanhToan;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.TimeLine;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.repository.GioHangChiTietRepository;
import com.example.datn.repository.GioHangRepository;
import com.example.datn.repository.HinhThucThanhToanRepository;
import com.example.datn.repository.HoaDonChiTietRepository;
import com.example.datn.repository.HoaDonRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.repository.PhuongThucThanhToanRepository;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.repository.TimeLineRepository;
import com.example.datn.service.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

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

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.getAllHoaDon();
    }

    @Override
    public List<HoaDon> getHoaDonHuy() {
        return hoaDonRepository.getHoaDonHuy();
    }

    @Override
    public List<HoaDon> getHoaDonKhachHang(Long idKhachHang) {
        return hoaDonRepository.getHoaDonKhachHang(idKhachHang);
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

    @Override
    @Transactional
    public void update(Long idHoaDon, Long idSanPhamChiTiet) {
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(idHoaDon);
        Optional<SanPhamChiTiet> sanPhamChiTietOpt = sanPhamChiTietRepository.findById(idSanPhamChiTiet);

        if (hoaDonOpt.isPresent() && sanPhamChiTietOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietOpt.get();
            List<HoaDonChiTiet> hoaDonChiTietList = hoaDon.getHoaDonChiTietList();
            BigDecimal tongTien = BigDecimal.ZERO;

            boolean updated = false;

            for (HoaDonChiTiet hdct : hoaDonChiTietList) {
                if (Objects.equals(hdct.getSanPhamChiTiet().getSanPhamChiTietId(), idSanPhamChiTiet)) {
                    hdct.setSoLuong(hdct.getSoLuong() + 1);

                    BigDecimal giaBan = sanPhamChiTiet.getGiaBan();
                    BigDecimal soLuong = new BigDecimal(hdct.getSoLuong());
                    BigDecimal donGia = giaBan.multiply(soLuong);
                    hdct.setDonGia(donGia);

                    hoaDonChiTietRepository.save(hdct);
                    updated = true;
                }

                tongTien = tongTien.add(hdct.getDonGia());
            }

            if (!updated) {
                HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                        .sanPhamChiTiet(sanPhamChiTiet)
                        .hoaDon(hoaDon)
                        .soLuong(1)
                        .trangThai(0)
                        .donGia(sanPhamChiTiet.getGiaBan())
                        .build();

                hoaDonChiTietRepository.save(hoaDonChiTiet);
                tongTien = tongTien.add(hoaDonChiTiet.getDonGia());
            }

            tienGiam(hoaDon, tongTien);
        }
    }


    @Override
    @Transactional
    public void stepDown(Long hoaDonId, Long hoaDonChiTietId) {
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(hoaDonId);
        Optional<HoaDonChiTiet> hoaDonChiTietOpt = hoaDonChiTietRepository.findById(hoaDonChiTietId);

        if (hoaDonOpt.isPresent() && hoaDonChiTietOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietOpt.get();

            int currentQuantity = hoaDonChiTiet.getSoLuong();
            if (currentQuantity > 1) {
                hoaDonChiTiet.setSoLuong(currentQuantity - 1);
                hoaDonChiTiet.setDonGia(BigDecimal.valueOf(currentQuantity - 1).multiply(hoaDonChiTiet.getSanPhamChiTiet().getGiaBan()));

                updateHoaDon(hoaDon, hoaDonChiTiet);
            }
        }
    }


    @Override
    @Transactional
    public void stepUp(Long hoaDonId, Long hoaDonChiTietId) {
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(hoaDonId);
        Optional<HoaDonChiTiet> hoaDonChiTietOpt = hoaDonChiTietRepository.findById(hoaDonChiTietId);

        if (hoaDonOpt.isPresent() && hoaDonChiTietOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietOpt.get();

            int currentQuantity = hoaDonChiTiet.getSoLuong();
            if (currentQuantity > 0) {
                hoaDonChiTiet.setSoLuong(currentQuantity + 1);
                hoaDonChiTiet.setDonGia(BigDecimal.valueOf(currentQuantity + 1).multiply(hoaDonChiTiet.getSanPhamChiTiet().getGiaBan()));

                updateHoaDon(hoaDon, hoaDonChiTiet);
            }
        }
    }

    @Override
    @Transactional
    public void updateThongTinGiaoHang(HoaDon hoaDon) {
        Optional<HoaDon> hd = hoaDonRepository.findById(hoaDon.getHoaDonId());
        if (hd.isPresent()) {
            hd.get().setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            hd.get().setEmail(hoaDon.getEmail());
            hd.get().setSdtNhan(hoaDon.getSdtNhan());
            hd.get().setDiaChiNhan(hoaDon.getDiaChiNhan());

            hoaDonRepository.save(hd.get());
        }
    }

    @Override
    @Transactional
    public void delete(Long hoaDonId, Long hoaDonChiTietId) {
        Optional<HoaDonChiTiet> hoaDonChiTietOpt = hoaDonChiTietRepository.findById(hoaDonChiTietId);
        if (hoaDonChiTietOpt.isPresent()) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietOpt.get();
            Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(hoaDonId);
            if (hoaDonOpt.isPresent()) {
                HoaDon hoaDon = hoaDonOpt.get();

                if (hoaDon.getHoaDonChiTietList().size() <= 1) {
                    throw new IllegalStateException("Cannot delete the only HoaDonChiTiet for this HoaDon.");
                }

                hoaDon.getHoaDonChiTietList().remove(hoaDonChiTiet);

                BigDecimal tongTien = hoaDon.getHoaDonChiTietList().stream()
                        .map(HoaDonChiTiet::getDonGia)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                tienGiam(hoaDon, tongTien);
                hoaDonChiTietRepository.delete(hoaDonChiTiet);
            }
        }
    }

    @Transactional
    @Override
    public void saveHoaDonTaiQuay(Long gioHangId, Long khachHangId, String discountCode, ThanhToanResponse thanhToanResponse) {
        HoaDon hoaDon = new HoaDon();

        khachHangRepository.findById(khachHangId).ifPresent(hoaDon::setKhachHang);

        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findByMaGiamGia(discountCode);
        if (phieuGiamGia != null) {
            hoaDon.setPhieuGiamGia(phieuGiamGia);
        }

        hoaDon.setLoaiHoaDon(true);
        hoaDon.setMaVanDon(generateInvoiceCode());
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTongTien(thanhToanResponse.getTongTien());
        hoaDon.setTienGiam(thanhToanResponse.getTienGiamGia());

        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);

        if (thanhToanResponse.getPaymentMethod() == 3) {
            hoaDon.setTenNguoiNhan(thanhToanResponse.getTenNguoiNhan());
            hoaDon.setEmail(thanhToanResponse.getEmail());
            hoaDon.setSdtNhan(thanhToanResponse.getSdt());
            hoaDon.setPhiShip(thanhToanResponse.getTienShip());
            hoaDon.setDiaChiNhan(thanhToanResponse.getDiaChi() + ", "
                    + thanhToanResponse.getWard() + ", "
                    + thanhToanResponse.getDistrict() + ", "
                    + thanhToanResponse.getProvince());
            hoaDon.setTrangThai(1);
            hoaDon.setThanhToan(false);
            timeLine.setTrangThai(1);
        } else {
            hoaDon.setTenNguoiNhan(null);
            hoaDon.setEmail(null);
            hoaDon.setSdtNhan(null);
            hoaDon.setPhiShip(BigDecimal.ZERO);
            hoaDon.setDiaChiNhan(null);
            hoaDon.setThanhToan(true);
            hoaDon.setTrangThai(6);
            timeLine.setTrangThai(6);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof MyUserDetail) {
                Long nhanVienId = ((MyUserDetail) principal).getId();
                nhanVienRepository.findById(nhanVienId).ifPresent(nhanVien -> {
                    hoaDon.setNhanVien(nhanVien);
                    timeLine.setNguoiThucHien(nhanVien.getMaNhanVien());
                });
            }
        }

        timeLine.setMoTa("Tạo hóa đơn thành công");
        timeLineRepository.save(timeLine);
        hoaDonRepository.save(hoaDon);

        phuongThucThanhToanRepository.findById(thanhToanResponse.getPaymentMethod()).ifPresent(phuongThucThanhToan -> {
            HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan();
            hinhThucThanhToan.setPhuongThucThanhToan(phuongThucThanhToan);
            hinhThucThanhToan.setHoaDon(hoaDon);
            hinhThucThanhToan.setTienThanhToan(thanhToanResponse.getTongTien());
            hinhThucThanhToanRepository.save(hinhThucThanhToan);
        });

        gioHangRepository.findById(gioHangId).ifPresent(gioHang -> {
            List<GioHangChiTiet> gioHangChiTietList = gioHang.getGioHangChiTietList();
            for (GioHangChiTiet ghct : gioHangChiTietList) {
                HoaDonChiTiet hoaDonChiTiet = getHoaDonChiTiet(ghct, hoaDon);
                hoaDonChiTietRepository.save(hoaDonChiTiet);

                sanPhamChiTietRepository.findById(ghct.getSanPhamChiTiet().getSanPhamChiTietId()).ifPresent(spct -> {
                    spct.setSoLuong(spct.getSoLuong() - ghct.getSoLuong());
                    sanPhamChiTietRepository.save(spct);
                });

                ghct.setGioHang(null);
            }
            gioHangChiTietRepository.saveAll(gioHangChiTietList);
            gioHangChiTietRepository.deleteAll(gioHangChiTietList);
        });
    }


    @Override
    @Transactional
    public String saveHoaDonOnline(Long khachHangId, PhieuGiamGiaResponse phieuGiamGiaResponse, ThanhToanResponse thanhToanResponse, List<CartItem> cartItems) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setLoaiHoaDon(false);
        hoaDon.setThanhToan(false);
        hoaDon.setTrangThai(1);
        hoaDon.setMaVanDon(generateInvoiceCode());
        hoaDon.setTenNguoiNhan(thanhToanResponse.getTenNguoiNhan());
        hoaDon.setEmail(thanhToanResponse.getEmail());
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setSdtNhan(thanhToanResponse.getSdt());
        hoaDon.setPhiShip(thanhToanResponse.getTienShip());
        hoaDon.setDiaChiNhan(thanhToanResponse.getDiaChi() + ", " + thanhToanResponse.getWard() + ", " + thanhToanResponse.getDistrict() + ", " + thanhToanResponse.getProvince());

        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findByMaGiamGia(phieuGiamGiaResponse.getMaPhieu());
        if (phieuGiamGia != null) {
            hoaDon.setTienGiam(phieuGiamGiaResponse.getTienGiam());
            hoaDon.setPhieuGiamGia(phieuGiamGia);
        }
        BigDecimal tongTien = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal giaBan = cartItem.getGia();
            BigDecimal soLuong = new BigDecimal(cartItem.getSoLuong());
            BigDecimal thanhTien = giaBan.multiply(soLuong);
            tongTien = tongTien.add(thanhTien);

        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setTrangThai(1);
        timeLine.setMoTa("Tạo hóa đơn thành công");

        if (khachHangId != null) {
            Optional<KhachHang> khachHang = khachHangRepository.findById(khachHangId);
            if (khachHang.isPresent()) {
                hoaDon.setKhachHang(khachHang.get());
                timeLine.setNguoiThucHien(khachHang.get().getHoTen());
            }
        }

        timeLineRepository.save(timeLine);
        tongTien = tongTien.subtract(phieuGiamGiaResponse.getTienGiam());
        hoaDon.setTongTien(tongTien.add(thanhToanResponse.getTienShip()));
        hoaDonRepository.save(hoaDon);

        for (CartItem cartItem : cartItems) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByName(cartItem.getTenSanPham(), cartItem.getKichThuoc(), cartItem.getMauSac());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setTrangThai(1);
            hoaDonChiTiet.setDonGia(cartItem.getGia());
            hoaDonChiTiet.setSoLuong(cartItem.getSoLuong());
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }
        Optional<PhuongThucThanhToan> phuongThucThanhToan = phuongThucThanhToanRepository.findById(thanhToanResponse.getPaymentMethod());
        if (phuongThucThanhToan.isPresent()) {
            HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan();
            hinhThucThanhToan.setPhuongThucThanhToan(phuongThucThanhToan.get());
            hinhThucThanhToan.setTienThanhToan(tongTien);
            hinhThucThanhToan.setHoaDon(hoaDon);
            hinhThucThanhToanRepository.save(hinhThucThanhToan);
        }


        return hoaDon.getMaVanDon();
    }

    private static HoaDonChiTiet getHoaDonChiTiet(GioHangChiTiet ghct, HoaDon hoaDon) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(ghct.getSanPhamChiTiet());
        hoaDonChiTiet.setSoLuong(ghct.getSoLuong());
        hoaDonChiTiet.setTrangThai(1);

        long soLuong = ghct.getSoLuong();
        BigDecimal giaBan = ghct.getSanPhamChiTiet().getGiaBan();
        BigDecimal soLuongBigDecimal = BigDecimal.valueOf(soLuong);
        BigDecimal donGia = soLuongBigDecimal.multiply(giaBan);
        hoaDonChiTiet.setDonGia(donGia);
        return hoaDonChiTiet;
    }

    private void tienGiam(HoaDon hoaDon, BigDecimal tongTien) {
        BigDecimal tienGiam = BigDecimal.ZERO;
        PhieuGiamGia phieuGiamGia = hoaDon.getPhieuGiamGia();

        if (phieuGiamGia != null) {
            if (tongTien.compareTo(phieuGiamGia.getGiaTriDonToiThieu()) >= 0) {
                if (phieuGiamGia.isLoaiPhieu()) {
                    BigDecimal discountPercent = new BigDecimal(phieuGiamGia.getGiaTriGiam()).divide(new BigDecimal(100));
                    tienGiam = tongTien.multiply(discountPercent);

                    if (tienGiam.compareTo(phieuGiamGia.getGiaTriGiamToiDa()) > 0) {
                        tienGiam = phieuGiamGia.getGiaTriGiamToiDa();
                    }
                } else {
                    BigDecimal discountPercent = new BigDecimal(phieuGiamGia.getGiaTriGiam()).divide(new BigDecimal(100));
                    tienGiam = tongTien.multiply(discountPercent);

                    if (tienGiam.compareTo(phieuGiamGia.getGiaTriGiamToiDa()) > 0) {
                        tienGiam = phieuGiamGia.getGiaTriGiamToiDa();
                    }
                }
            }
        }

        BigDecimal finalTotal = tongTien.subtract(tienGiam).add(hoaDon.getPhiShip() != null ? hoaDon.getPhiShip() : BigDecimal.ZERO);
        hoaDon.setTienGiam(tienGiam);
        hoaDon.setTongTien(finalTotal);

        hoaDonRepository.save(hoaDon);
    }

    private void updateHoaDon(HoaDon hoaDon, HoaDonChiTiet hoaDonChiTiet) {
        BigDecimal tongTien = BigDecimal.ZERO;
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDon.getHoaDonChiTietList();
        for (HoaDonChiTiet hdct : hoaDonChiTietList) {
            tongTien = tongTien.add(hdct.getDonGia());
        }

        tienGiam(hoaDon, tongTien);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    private String generateInvoiceCode() {
        long time = System.currentTimeMillis();
        String timeBase36 = Long.toString(time, 36).toUpperCase();
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        return String.format("HD%s%03d", timeBase36, randomNum);
    }

}
