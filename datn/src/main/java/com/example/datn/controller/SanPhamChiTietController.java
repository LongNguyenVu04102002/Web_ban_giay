package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/sanpham")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private HinhAnhService hinhAnhService;

    @GetMapping("/bienthegiay")
    public String show(Model model) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.getAll();
        List<SanPhamChiTietResponse> sanPhamChiTietResponses = new ArrayList<>();

        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList){
            SanPhamChiTietResponse sanPhamChiTietResponse = new SanPhamChiTietResponse();
            sanPhamChiTietResponse.setSanPhamChiTiet(sanPhamChiTiet);
            sanPhamChiTietResponse.setDataImg(Base64.getEncoder().encodeToString(hinhAnhService.getImageBySanPhamChiTietIdWithPriority(sanPhamChiTiet.getSanPhamChiTietId(), 1)));
            sanPhamChiTietResponses.add(sanPhamChiTietResponse);
        }

        model.addAttribute("sanPhamChiTietList", sanPhamChiTietResponses);
        return "admin/includes/content/sanpham/bienthegiay/home";
    }

    @GetMapping("/bienthegiay/form")
    public String form(Model model) {
        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
        model.addAttribute("spct", new SanPhamChiTiet());
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return getString(model);
    }

    @GetMapping("/bienthegiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getById(id);
        List<HinhAnh> hinhAnhList = hinhAnhService.getImagesBySanPhamChiTietId(id);
        List<String> encodedImages = hinhAnhList.stream()
                .map(hinhAnh -> Base64.getEncoder().encodeToString(hinhAnh.getDataImg()))
                .collect(Collectors.toList());
        model.addAttribute("spct", sanPhamChiTiet);
        model.addAttribute("encodedImages", encodedImages);
        return getStringUpdate(model);
    }

    @PostMapping("/bienthegiay/save-update")
    public String saveUpdate(SanPhamChiTiet sanPhamChiTiet, @RequestParam("image") MultipartFile[] images) throws IOException {
        List<byte[]> imageDatas = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                byte[] imageData = image.getBytes();
                imageDatas.add(imageData);
            }
        }
        hinhAnhService.saveOrUpdateImages(sanPhamChiTiet, imageDatas);
        sanPhamChiTietService.saveOfUpdate(sanPhamChiTiet);
        return "redirect:/admin/sanpham/bienthegiay";
    }

    private String getString(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lsMauSac", mauSacService.getAll());
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        return "admin/includes/content/sanpham/bienthegiay/form";
    }

    private String getStringUpdate(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lsMauSac", mauSacService.getAll());
        model.addAttribute("lstSanPham", sanPhamService.getAll());
        return "admin/includes/content/sanpham/bienthegiay/form-update";
    }

    //update trang thai
    @PostMapping("/bienthegiay/update/{id}")
    public String update(@PathVariable Long id) {
        sanPhamChiTietService.update(id);
        return "redirect:/admin/sanpham/bienthegiay";
    }
}
