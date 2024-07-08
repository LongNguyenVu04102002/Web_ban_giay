package com.example.datn.service;

import com.example.datn.entity.HoaDon;
import com.example.datn.model.response.HoaDonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface HoaDonService {
    List<HoaDon> getAllHoaDon();

    List<HoaDon> getHoaDonChoXacNhan();

    List<HoaDon> getHoaDonDaXacNhan();

    List<HoaDon> getHoaDonChoGiaoHang();

    List<HoaDon> getHoaDonDangGiaoHang();

    List<HoaDon> getHoaDonDaGiaoHang();

    List<HoaDon> getHoaDonHoanThanh();

    List<HoaDon> getHoaDonHuy();

    HoaDon getHoaDonById(Long id);

    ResponseEntity<?> addHoaDon(HoaDonResponse hoaDonResponse);

    ResponseEntity<?> getAll();

}
