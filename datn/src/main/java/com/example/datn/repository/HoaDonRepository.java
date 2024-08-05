package com.example.datn.repository;

import com.example.datn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("select p from HoaDon p order by p.hoaDonId desc")
    List<HoaDon> getAllHoaDon();

    @Query("select p from HoaDon p where p.trangThai = 1 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonChoXacNhan();

    @Query("select p from HoaDon p where p.trangThai = 2 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonDaXacNhan();

    @Query("select p from HoaDon p where p.trangThai = 3 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonChoGiaoHang();

    @Query("select p from HoaDon p where p.trangThai = 4 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonDangGiaoHang();

    @Query("select p from HoaDon p where p.trangThai = 5 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonDaGiaoHang();

    @Query("select p from HoaDon p where p.trangThai = 6 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonHoanThanh();

    @Query("select p from HoaDon p where p.trangThai = 7 order by p.hoaDonId desc")
    List<HoaDon> getHoaDonHuy();

    @Query("select t.hoaDon from TimeLine t where t.ngayTao = :ngayTao")
    List<HoaDon> findByHoaDonIdIn(@Param("ngayTao") LocalDate ngayTao);
}
