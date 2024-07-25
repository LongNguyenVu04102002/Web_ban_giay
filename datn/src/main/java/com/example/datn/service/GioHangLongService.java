package com.example.datn.service;
import com.example.datn.entity.GioHang;
import com.example.datn.entity.GioHangChiTiet;
import com.example.datn.repository.GioHangChiTietRepository;
import com.example.datn.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangLongService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;



    public GioHang saveGioHang(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    public GioHangChiTiet saveGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    public List<GioHangChiTiet> getAllGioHangChiTietByGioHangId(Long gioHangId) {
        return gioHangChiTietRepository.findAll();
    }

    public void deleteGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        gioHangChiTietRepository.delete(gioHangChiTiet);
    }
}
