package com.example.datn.api;

import com.example.datn.entity.DiaChi;
import com.example.datn.service.Impl.DiaChiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/diachi")
public class KhachHangApi {

    @Autowired
    private DiaChiServiceImpl diaChiService;

    @GetMapping("/khachhang/{khachHangId}")
    public ResponseEntity<List<DiaChi>> getDiaChiByKhachHangId(@PathVariable Long khachHangId) {
        List<DiaChi> diaChiList = diaChiService.findByKhachHangId(khachHangId);
        return ResponseEntity.ok(diaChiList);
    }
}
