package com.example.datn.api;

import com.example.datn.service.impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("sanpham")
public class SanPhamApi {

    @Autowired
    private SanPhamServiceImpl sanPhamService;

//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllSanPham(){
//        return sanPhamService.getAllSanPham();
//    }
//
//    @GetMapping("/getById/{id}")
//    public ResponseEntity<?> getSanPhamById(@PathVariable Long id) {
//        return sanPhamService.getAllSanPhamById(id);
//    }

}
