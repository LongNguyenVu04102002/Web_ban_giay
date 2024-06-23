package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

  @GetMapping("/hienthi")
  public ResponseEntity<?> getAllNhanVien(){
      return nhanVienService.getAllNhanVien();
  }
  @PostMapping("/save")
    public ResponseEntity<?> addNhanVien(@RequestBody NhanVien nhanVien){
      return nhanVienService.addNhanVien(nhanVien);
  }

  @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNhanVien(@RequestBody NhanVien nhanVien,@PathVariable Long id){
      return nhanVienService.updateNhanVien(nhanVien,id);
  }
  @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable Long id){
      return nhanVienService.deleteNhanVien(id);
  }
}
