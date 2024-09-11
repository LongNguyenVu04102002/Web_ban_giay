package com.example.datn.controller;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.model.request.HinhAnhDTORequest;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setTrangThai(true);
        model.addAttribute("spct", spct);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return getString(model);
    }

    @GetMapping("/bienthegiay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getById(id);
        List<HinhAnh> hinhAnhList = hinhAnhService.getImagesBySanPhamChiTietId(id);
        List<HinhAnhDTORequest> anhRequests = hinhAnhList.stream()
                .map(hinhAnh -> new HinhAnhDTORequest(hinhAnh.getHinhAnhId(), Base64.getEncoder().encodeToString(hinhAnh.getDataImg())))
                .collect(Collectors.toList());
        model.addAttribute("spct", sanPhamChiTiet);
        model.addAttribute("imageDTOs", anhRequests);
        return getStringUpdate(model);
    }

    @PostMapping("/bienthegiay/save-update")
    public String saveUpdate(SanPhamChiTiet sanPhamChiTiet,
                             @RequestParam("image") MultipartFile[] images,
                             @RequestParam(value = "imageId", required = false) Long[] imageIds,
                             @RequestParam("imageChanged") String imageChanged,
                             @RequestParam(value = "deletedImageIds", required = false) String deletedImageIds) throws IOException {

        if (sanPhamChiTiet.getGiaBan() == null || sanPhamChiTiet.getGiaBan().compareTo(new BigDecimal("1000")) < 0 ||
                sanPhamChiTiet.getSoLuong() == null || sanPhamChiTiet.getSoLuong() < 1) {
            return "redirect:/admin/includes/content/sanpham/bienthegiay/home";
        }

        if (imageIds != null) {
            for (Long imageId : imageIds) {
                System.out.println("Image ID: " + imageId);
            }
        } else {
            System.out.println("No Image IDs received");
        }

        if (deletedImageIds != null && !deletedImageIds.isEmpty()) {
            String[] idsToDelete = deletedImageIds.split(",");
            for (String id : idsToDelete) {
                hinhAnhService.deleteImageById(Long.parseLong(id));
            }
        }

        if (imageChanged.equals("true")) {
            List<byte[]> imageDatas = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    byte[] imageData = image.getBytes();
                    imageDatas.add(imageData);
                }
            }
            hinhAnhService.saveOrUpdateImages(sanPhamChiTiet, imageDatas, imageIds);
        }
        sanPhamChiTietService.saveOfUpdate(sanPhamChiTiet);
        return "redirect:/admin/sanpham/bienthegiay";
    }

    private String getString(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.findAllByTrangThaiTrue());
        model.addAttribute("lsMauSac", mauSacService.findAllByTrangThaiTrue());
        model.addAttribute("lstSanPham", sanPhamService.findAllByTrangThaiTrue());
        return "admin/includes/content/sanpham/bienthegiay/form";
    }

    private String getStringUpdate(Model model) {
        model.addAttribute("lstKichThuoc", kichThuocService.findAllByTrangThaiTrue());
        model.addAttribute("lsMauSac", mauSacService.findAllByTrangThaiTrue());
        model.addAttribute("lstSanPham", sanPhamService.findAllByTrangThaiTrue());
        return "admin/includes/content/sanpham/bienthegiay/form-update";
    }

    //update trang thai
    @PostMapping("/bienthegiay/update/{id}")
    public String update(@PathVariable Long id) {
        sanPhamChiTietService.update(id);
        return "redirect:/admin/sanpham/bienthegiay";
    }
}
