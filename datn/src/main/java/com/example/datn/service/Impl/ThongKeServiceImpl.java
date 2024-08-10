package com.example.datn.service.Impl;

import com.example.datn.entity.*;
import com.example.datn.repository.*;
import com.example.datn.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public List<SanPhamChiTiet> listSanPhamChiTietBySanPhamAndMauSac(Long idSp, Long idMs) {
        return sanPhamChiTietRepository.findAllBySanPham_SanPhamIdAndMauSac_MauSacIdOrderBySanPhamChiTietId(idSp, idMs);
    }

    @Override
    public List<String> getHoaDonToday() {
        LocalDate today = LocalDate.now();
        List<HoaDon> hoaDons = hoaDonRepository.findByHoaDonIdIn(today);
        return hoaDons.stream()
                .map(this::convertToFormattedString)
                .collect(Collectors.toList());
    }

    private String convertToFormattedString(HoaDon hoaDon) {
        String maVanDon = hoaDon.getMaVanDon();
        BigDecimal tongTien = hoaDon.getTongTien();
        String trangThai = convertTrangThai(hoaDon.getTrangThai());
        return String.format("%s %.2f VNĐ %s", maVanDon, tongTien, trangThai);
    }

    private String convertTrangThai(int trangThai) {
        switch (trangThai) {
            case 1:
                return "Chờ xác nhận";
            case 2:
                return "Đã xác nhận";
            case 3:
                return "Chờ giao hàng";
            case 4:
                return "Đang giao hàng";
            case 5:
                return "Đã giao hàng";
            case 6:
                return "Hoàn thành";
            case 7:
                return "Hủy";
            default:
                return "Không xác định";
        }
    }

    @Override
    public Long countKhachHangByNgayTaoAndTrangThai(LocalDate ngayTao, int trangThai) {
        return timeLineRepository.countKhachHangByNgayTaoAndTrangThai(ngayTao, trangThai);
    }

    @Override
    public Long countKhachHangByYearMonthAndTrangThai(int year, int month, int trangThai) {
        return timeLineRepository.countKhachHangByYearMonthAndTrangThai(year, month, trangThai);
    }

    @Override
    public Long countHoaDonByNgayTaoAndTrangThai(LocalDate ngayTao, int trangThai) {
        return timeLineRepository.countHoaDonByNgayTaoAndTrangThai(ngayTao, trangThai);
    }

    @Override
    public Long countHoaDonByYearMonthAndTrangThai(int year, int month, int trangThai) {
        return timeLineRepository.countHoaDonByYearMonthAndTrangThai(year, month, trangThai);
    }

    @Override
    public List<Long> countHoaDonForCurrentWeek(int trangThai) {
        LocalDate currentDate = LocalDate.now();

        List<LocalDate> weekDays = new ArrayList<>();

        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);

        for (int i = 0; i < 7; i++) {
            weekDays.add(startOfWeek.plusDays(i));
        }

        List<Long> hoaDonCounts = new ArrayList<>();
        for (LocalDate date : weekDays) {
            Long count = countKhachHangByNgayTaoAndTrangThai(date, trangThai);
            hoaDonCounts.add(count);
        }
        return hoaDonCounts;
    }

    @Override
    public List<ThuongHieu> listThuongHieu() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public List<SanPham> listSanPhamByThuongHieu(Long id) {
        return sanPhamRepository.findAllByThuongHieu_ThuongHieuId(id);
    }

    @Override
    public List<MauSac> listMauSacBySanPham(Long id) {
        return mauSacRepository.getAllBySanPham(id);
    }

    @Override
    public List<String> nameSpOfSanPhamChiTiet(Long idSp, Long idMs) {
        List<SanPhamChiTiet> sanPhamChiTietList = listSanPhamChiTietBySanPhamAndMauSac(idSp, idMs);

        List<String> names = new ArrayList<>();
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList) {
            String name = sanPhamChiTiet.getKichThuoc().getTen();
            names.add(name);
        }
        return names;
    }

    @Override
    public List<Integer> countSoLuongTonOfSanPhamChiTiet(Long idSp, Long idMs) {
        List<SanPhamChiTiet> sanPhamChiTietList = listSanPhamChiTietBySanPhamAndMauSac(idSp, idMs);

        List<Integer> soLuongTons = new ArrayList<>();
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList) {
            Integer sl = sanPhamChiTiet.getSoLuong();
            soLuongTons.add(sl);
        }
        return soLuongTons;
    }

    @Override
    public List<Integer> countSoLuongBanOfSanPhamChiTiet(Long idSp, Long idMs) {
        List<SanPhamChiTiet> sanPhamChiTietList = listSanPhamChiTietBySanPhamAndMauSac(idSp, idMs);

        List<Integer> soLuongBans = new ArrayList<>();
        for (SanPhamChiTiet sanPhamChiTiet :
                sanPhamChiTietList) {
            List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findBySanPhamChiTietAndHoaDon_TrangThai(sanPhamChiTiet, 6);
            Integer sl = hoaDonChiTietList.stream()
                    .mapToInt(HoaDonChiTiet::getSoLuong)
                    .sum();
            soLuongBans.add(sl);
        }
        return soLuongBans;
    }

    @Override
    public List<BigDecimal> giaBanOfSanPhamChiTiet(Long idSp, Long idMs) {
        List<SanPhamChiTiet> sanPhamChiTietList = listSanPhamChiTietBySanPhamAndMauSac(idSp, idMs);

        List<BigDecimal> giaBans = new ArrayList<>();
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList) {
            BigDecimal gb = sanPhamChiTiet.getGiaBan();
            giaBans.add(gb);
        }
        return giaBans;
    }

    @Override
    public List<Long> countsDonHangByMonth(Integer start, Integer end, Integer trangThai) {
        YearMonth currentMonth = YearMonth.now();
        List<Integer> numbers = getNumbersInRange(start, end);

        List<Long> soLuongDons = new ArrayList<>();
        for (Integer i : numbers) {
            Long sl = timeLineRepository.countHoaDonByYearMonthAndTrangThai(currentMonth.getYear(), i, trangThai);
            soLuongDons.add(sl);
        }
        return soLuongDons;
    }

    @Override
    public List<Integer> sumDonHangByMonth(Integer start, Integer end, Integer trangThai) {
        YearMonth currentMonth = YearMonth.now();
        List<Integer> numbers = getNumbersInRange(start, end);

        List<Integer> soLuongDons = new ArrayList<>();
        for (Integer i : numbers) {
            Integer sl = timeLineRepository.sumHoaDonByYearMonthAndTrangThai(currentMonth.getYear(), i, trangThai);
            if (sl == null) {
                soLuongDons.add(0);
            } else {
                soLuongDons.add(sl);
            }
        }
        return soLuongDons;
    }

    @Override
    public Integer sumDonHangByDay(Integer trangThai) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return timeLineRepository.sumHoaDonByYearMonthDayAndTrangThai(year, month, day, trangThai);
    }

    @Override
    public List<Integer> getNumbersInRange(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
