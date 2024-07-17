package com.example.datn.api;

import com.example.datn.service.impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sanphamchitiet")
public class SanPhamChiTietApi {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSanPhamChiTiet() {
        return sanPhamChiTietService.getAllSanPhamChiTiet() ;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSanPhamChiTietById(@PathVariable Long id) {
        return sanPhamChiTietService.getSanPhamChiTietById(id);
    }

}
