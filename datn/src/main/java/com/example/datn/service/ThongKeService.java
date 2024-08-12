package com.example.datn.service;

import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.ThuongHieu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {

    List<String> getHoaDonToday();

    Long countKhachHangByYearMonthAndTrangThai(int year, int month, int trangThai);

    Long countHoaDonByNgayTaoAndTrangThai(LocalDate ngayTao, int trangThai);

    Long countHoaDonByYearMonthAndTrangThai(int year, int month, int trangThai);

    List<Long> countHoaDonForCurrentWeek(int trangThai);

    List<ThuongHieu> listThuongHieu();

    List<SanPham> listSanPhamByThuongHieu(Long id);

    List<MauSac> listMauSacBySanPham(Long id);

    List<String> nameSpOfSanPhamChiTiet(Long idSp, Long idMs);

    List<Integer> countSoLuongTonOfSanPhamChiTiet(Long idSp, Long idMs);

    List<Integer> countSoLuongBanOfSanPhamChiTiet(Long idSp, Long idMs);

    List<BigDecimal> giaBanOfSanPhamChiTiet(Long idSp, Long idMs);

    List<Long> countsDonHangByMonth(Integer start, Integer end, Integer trangThai);

    List<Integer> sumDonHangByMonth(Integer start, Integer end, Integer trangThai);

    Integer sumDonHangByDay(Integer trangThai);

    List<Integer> getNumbersInRange(int start, int end);
}
