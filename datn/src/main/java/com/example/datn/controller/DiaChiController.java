package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diachi")
public class DiaChiController {

    @Autowired
    DiaChiService diaChiService;

    @GetMapping
    public ResponseEntity<?> getAllDiaChi(){
        return  diaChiService.getAllDiaCHi();
    }
    @PostMapping("/save")
    public ResponseEntity<?> addDiaCHi(@RequestBody DiaChi diaChi){
        return diaChiService.addDiaChi(diaChi);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDiaChi(@RequestBody DiaChi diaChi,@PathVariable Long id){
        return diaChiService.updateDiaChi(diaChi,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDiaChi(@PathVariable Long id){
        return diaChiService.deleteDiaChi(id);
    }
}
