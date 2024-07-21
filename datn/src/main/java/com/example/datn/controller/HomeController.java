package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.service.impl.KichThuocServiceImpl;
import com.example.datn.service.impl.MauSacServiceImpl;
import com.example.datn.service.impl.SanPhamServiceImpl;
import com.example.datn.service.impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private SanPhamServiceImpl chiTietService;

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    @Autowired
    private MauSacServiceImpl mauSacService;

    @GetMapping("/home")
    public String home() {
        return "user/includes/content/home";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        List<SanPham> sanPhamList = sanPhamService.getAll();
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("kichThuocList", kichThuocList);
        return "user/includes/content/shop";
    }

    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);

        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        List<KichThuoc> uniqueSizes = kichThuocList.stream()
                .collect(Collectors.toMap(KichThuoc::getKichThuocId, kt -> kt, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());

        model.addAttribute("uniqueSizes", uniqueSizes);

        List<MauSac> mauSacList = mauSacService.getAll();
        List<MauSac> uniqueColors = mauSacList.stream()
                .collect(Collectors.toMap(MauSac::getMauSacId, kt -> kt, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());

        model.addAttribute("uniqueColors", uniqueColors);
        return "user/includes/content/detail";
    }
    @GetMapping("/about")
    public String about() {
        return "user/includes/content/about";
    }

    @GetMapping("/blog")
    public String blog() {
        return "user/includes/content/blog";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user/includes/content/contact";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "user/includes/content/checkout";
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/includes/content/cart";
    }

}
