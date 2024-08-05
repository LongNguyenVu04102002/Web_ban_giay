package com.example.datn.controller;

import com.example.datn.service.Impl.MauSacServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.datn.service.KhachHangService;
import com.example.datn.service.SanPhamService;
import com.example.datn.service.ThuongHieuService;
import com.example.datn.service.KichThuocService;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.MauSac;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private MauSacServiceImpl mauSacService;

    private void addAuthenticationInfo(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String email = authentication.getName();
            KhachHang khachHang = khachHangService.getAllByEmail(email);
            if (khachHang != null) {
                model.addAttribute("isAuthenticated", true);
                model.addAttribute("username", khachHang.getHoTen());
            } else {
                model.addAttribute("isAuthenticated", false);
            }
        } else {
            model.addAttribute("isAuthenticated", false);
        }
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        return "user/includes/content/home";
    }

    @GetMapping("/shop")
    public String shop(Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        List<SanPham> sanPhamList = sanPhamService.getAll();
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("kichThuocList", kichThuocList);
        return "user/includes/content/shop";
    }

    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id, Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            return "redirect:/error";
        }
        model.addAttribute("sanPham", sanPham);

        List<KichThuoc> uniqueSizes = sanPham.getSanPhamChiTietList().stream()
                .map(SanPhamChiTiet::getKichThuoc)
                .distinct()
                .sorted(Comparator.comparingInt(kt -> Integer.parseInt(kt.getTen())))
                .collect(Collectors.toList());

        model.addAttribute("uniqueSizes", uniqueSizes);

        List<MauSac> uniqueColors = sanPham.getSanPhamChiTietList().stream()
                .map(SanPhamChiTiet::getMauSac)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("uniqueColors", uniqueColors);

        return "user/includes/content/detail";
    }

    @GetMapping("/about")
    public String about(Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        return "user/includes/content/about";
    }

    @GetMapping("/blog")
    public String blog(Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        return "user/includes/content/blog";
    }

    @GetMapping("/contact")
    public String contact(Model model, Authentication authentication) {
        addAuthenticationInfo(model, authentication);
        return "user/includes/content/contact";
    }
}
