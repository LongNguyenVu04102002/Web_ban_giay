package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.service.*;
import com.example.datn.service.Impl.HinhAnhServiceImpl;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import com.example.datn.service.Impl.MauSacServiceImpl;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    @Autowired
    private MauSacServiceImpl mauSacService;

    @Autowired
    private HinhAnhServiceImpl hinhAnhService;

//    @Autowired
//    private DotGiamGiaServiceImpl dotGiamGiaService;

    @GetMapping("/bienthegiay")
    public String show(Model model) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.getAll();

        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);

        List<SanPhamChiTietResponse> sanPhamChiTietResponses = new ArrayList<>();
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        List<MauSac> mauSacList = mauSacService.getAll();

        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList){
            SanPhamChiTietResponse sanPhamChiTietResponse = new SanPhamChiTietResponse();
            sanPhamChiTietResponse.setSanPhamChiTiet(sanPhamChiTiet);
            sanPhamChiTietResponse.setDataImg(Base64.getEncoder().encodeToString(hinhAnhService.getImageBySanPhamChiTietIdWithPriority(sanPhamChiTiet.getSanPhamChiTietId(), 1)));
            sanPhamChiTietResponses.add(sanPhamChiTietResponse);
        }

        model.addAttribute("mauSac", mauSacList);
        model.addAttribute("kichThuoc", kichThuocList);
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
        model.addAttribute("spct", sanPhamChiTiet);
        return getStringUpdate(model);
    }

    @PostMapping("/bienthegiay/save")
    public String save(@ModelAttribute("sanPhamChiTietResponse") SanPhamChiTietResponse sanPhamChiTietResponse) {
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietResponse.getSanPhamChiTietList();
        List<HinhAnh> hinhAnhList = new ArrayList<>();
        hinhAnhService.save(hinhAnhList);
        sanPhamChiTietService.save(sanPhamChiTietList);
        return "redirect:/admin/sanpham/bienthegiay";
    }

    @PostMapping("/bienthegiay/save-update")
    public String saveUpdate(SanPhamChiTiet sanPhamChiTiet,
                             @RequestParam("image") MultipartFile[] images,
                             @RequestParam(value = "imageId", required = false) Long[] imageIds,
                             @RequestParam("imageChanged") String imageChanged) throws IOException {

        System.out.println("Received Image IDs:");
        if (imageIds != null) {
            for (Long imageId : imageIds) {
                System.out.println("Image ID: " + imageId);
            }
        } else {
            System.out.println("No Image IDs received");
        }

        if (imageChanged.equals("true")) {
        List<byte[]> imageDatas = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                byte[] imageData = image.getBytes();
                imageDatas.add(imageData);
            }
        }
        hinhAnhService.saveOrUpdateImages(sanPhamChiTiet, imageDatas, imageIds);}
        sanPhamChiTietService.saveOfUpdate(sanPhamChiTiet);
        return "redirect:/admin/sanpham/bienthegiay";
    }

    private String getString(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.getKichThuocsByTrangThai(false));
        model.addAttribute("lsMauSac", mauSacService.getMauSacsByTrangThai(false));
        model.addAttribute("lstSanPham", sanPhamService.getSanPhamsByTrangThai(false));
        return "admin/includes/content/sanpham/bienthegiay/form";
    }

    private String getStringUpdate(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.getKichThuocsByTrangThai(false));
        model.addAttribute("lsMauSac", mauSacService.getMauSacsByTrangThai(false));
        model.addAttribute("lstSanPham", sanPhamService.getSanPhamsByTrangThai(false));
        return "admin/includes/content/sanpham/bienthegiay/form-update";
    }

    @PostMapping("/bienthegiay/update/{id}")
    public String update(@PathVariable Long id) {
        sanPhamChiTietService.update(id);
        return "redirect:/admin/sanpham/bienthegiay";
    }
}
