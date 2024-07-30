package com.example.datn.service;

import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {

    List<String> getHoaDonToday();

    Long countKhachHangByNgayTaoAndTrangThai(LocalDate ngayTao, int trangThai);

    Long countKhachHangByYearMonthAndTrangThai(int year, int month, int trangThai);

    Long countHoaDonByNgayTaoAndTrangThai(LocalDate ngayTao, int trangThai);

    Long countHoaDonByYearMonthAndTrangThai(int year, int month, int trangThai);

    List<Long> countHoaDonForCurrentWeek(int trangThai);
}
