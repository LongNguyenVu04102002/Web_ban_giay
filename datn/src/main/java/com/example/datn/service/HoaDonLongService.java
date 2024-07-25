package com.example.datn.service;

import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.repository.HoaDonChiTietRepository;
import com.example.datn.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonLongService {

    @Autowired
    private HoaDonRepository hoaDonRepository;



    public void save(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }
}
