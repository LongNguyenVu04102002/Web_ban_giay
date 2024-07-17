package com.example.datn.api;

import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.service.impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/sanphamchitiet")
public class SanPhamChiTietApi {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSanPhamChiTiet() {
        return sanPhamChiTietService.getAllSanPhamChiTiet();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSanPhamChiTietById(@PathVariable Long id) {
        return sanPhamChiTietService.getSanPhamChiTietById(id);
    }

    @PostMapping("/bienthegiay/save")
    public ResponseEntity<?> save(@RequestBody SanPhamChiTietResponse sanPhamChiTietResponse) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietResponse.getSanPhamChiTietList();
        sanPhamChiTietService.save(sanPhamChiTietList);
        return ResponseEntity.ok(sanPhamChiTietList);
    }

}
