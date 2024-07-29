package com.example.datn.service.impl;

import com.example.datn.entity.HoaDon;
import com.example.datn.repository.HoaDonRepository;
import com.example.datn.repository.TimeLineRepository;
import com.example.datn.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;

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
}
