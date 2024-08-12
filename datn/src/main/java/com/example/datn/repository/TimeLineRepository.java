package com.example.datn.repository;

import com.example.datn.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    @Query("select p from TimeLine p where p.hoaDon.hoaDonId = :hoaDonId")
    List<TimeLine> findByHoaDonId(Long hoaDonId);


    @Query("select count(distinct t.hoaDon.khachHang.khachHangId) from TimeLine t where t.ngayTao = :ngayTao and t.hoaDon.trangThai = :trangThai")
    Long countKhachHangByNgayTaoAndTrangThai(@Param("ngayTao") LocalDate ngayTao, @Param("trangThai") int trangThai);

    @Query("select count(distinct t.hoaDon.khachHang.khachHangId) from TimeLine t where year(t.ngayTao) = :year and month(t.ngayTao) = :month and t.hoaDon.trangThai = :trangThai")
    Long countKhachHangByYearMonthAndTrangThai(@Param("year") int year, @Param("month") int month, @Param("trangThai") int trangThai);

    @Query("select count(t.hoaDon.hoaDonId) from TimeLine t where t.ngayTao = :ngayTao and t.hoaDon.trangThai = :trangThai")
    Long countHoaDonByNgayTaoAndTrangThai(@Param("ngayTao") LocalDate ngayTao, @Param("trangThai") int trangThai);

    @Query("select count(t.hoaDon.hoaDonId) from TimeLine t where year(t.ngayTao) = :year and month(t.ngayTao) = :month and t.hoaDon.trangThai = :trangThai")
    Long countHoaDonByYearMonthAndTrangThai(@Param("year") int year, @Param("month") int month, @Param("trangThai") int trangThai);

    @Query("select sum (t.hoaDon.tongTien - coalesce(t.hoaDon.tienGiam, 0)) from TimeLine t where year(t.ngayTao) = :year and month(t.ngayTao) = :month and t.hoaDon.trangThai = :trangThai")
    Integer sumHoaDonByYearMonthAndTrangThai(@Param("year") int year, @Param("month") int month, @Param("trangThai") int trangThai);

    @Query("select sum(t.hoaDon.tongTien - coalesce(t.hoaDon.tienGiam, 0)) from TimeLine t " +
            "where year(t.ngayTao) = :year and month(t.ngayTao) = :month and day(t.ngayTao) = :day " +
            "and t.hoaDon.trangThai = :trangThai")
    Integer sumHoaDonByYearMonthDayAndTrangThai(@Param("year") int year,
                                                @Param("month") int month,
                                                @Param("day") int day,
                                                @Param("trangThai") int trangThai);


    @Query("select t from TimeLine t where t.ngayTao = :ngayTao")
    List<TimeLine> findByNgayTao(@Param("ngayTao") LocalDate ngayTao);
}
