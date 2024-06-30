package com.example.datn.service;

import com.example.datn.entity.HoaDon;
import com.example.datn.model.response.HoaDonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface HoaDonService {
    List<HoaDon> getAllHoaDon();

    HoaDon getHoaDonById(Long id);

    ResponseEntity<?> addHoaDon(HoaDonResponse hoaDonResponse);

}
