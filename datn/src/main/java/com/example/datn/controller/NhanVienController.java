package com.example.datn.controller;

import com.example.datn.entity.NhanVien;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

  @GetMapping("/nhanvien")
public  String listNhanVien(Model model,
                            NhanVien nhanVien,
                            @RequestParam(defaultValue = "1")int page,
                            @RequestParam(defaultValue = "5")int size){

    Page<NhanVien> nhanVienPage= nhanVienService.getAllNhanVienByPage(page,size);
    model.addAttribute("nhanviens",nhanVienPage.getContent());
      model.addAttribute("currentPage", nhanVienPage.getNumber() + 1); // Vị trí trang hiện tại
      model.addAttribute("totalPages", nhanVienPage.getTotalPages()); // Tổng số trang
      model.addAttribute("nhanVien", new NhanVien()); // Thêm đối tượng khachHang vào model

      nhanVien.setTrangThai(true);
      return "left-menu-nhan-vien";

  }
  @GetMapping("/addNhanVien")
    public  String add(Model model,NhanVien nhanVien){
      model.addAttribute("NhanVien",nhanVien);
      return "nhanvien/add";
  }
  @GetMapping("/nhanvien/{nhanVienId}/toggle")
    public  String toggleTrangThai(@PathVariable Long nhanVienId){
      nhanVienService.toggleTrangThai(nhanVienId);
      return "redirect:/nhanvien";
  }
    @GetMapping("/editNhanVien/{id}")
    public  String edit(@PathVariable Long id , Model model){
        Optional<NhanVien> nhanVien = nhanVienService.getNhanVienById(id);
        if (nhanVien.isPresent()){
            model.addAttribute("nhanVien",nhanVien.get());
            return "nhanvien/edit";
        }else {
            return "redirect:/nhanvien";
        }
    }
    @PostMapping("/editNhanVien/{id}")
    public  String editNhanVien(@PathVariable Long id,@ModelAttribute NhanVien nhanVien){
      nhanVien.setNhanVienId(id);
      nhanVienService.saveNhanVien(nhanVien);
      return "redirect:nhanvien";
    }
    @PostMapping("/saveNhanVien")
    public String addNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien){
      nhanVienService.saveNhanVien(nhanVien);
      return "redirect:/nhanvien";
    }
    @GetMapping("/deleteNhanVien/{id}")
    public String deleteNhanVien(@PathVariable Long id){
      nhanVienService.deleteNhanVien(id);
      return "redirect:/nhanvien";
    }

}
