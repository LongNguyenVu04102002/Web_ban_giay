package com.example.datn.controller;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/giamgia")
public class PhieuGiamGiaController {
    @Autowired
    PhieuGiamGiaService service;

    @GetMapping()
    public ResponseEntity<?> getAllPhieu() {

        return service.getAllPhieuGiamGia();
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePhieuGiamGia( @RequestBody PhieuGiamGia phieuGiamGia) {
        return service.addPhieuGiamGia(phieuGiamGia);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePhieuGiamGia(@RequestBody PhieuGiamGia phieuGiamGia, @PathVariable Long id){
        return service.updatePhieuGiamGia(phieuGiamGia, id);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> deletePhieuGiamGia(@PathVariable Long id){
        return service.deletePhieuGiamGia(id);
    }

}
