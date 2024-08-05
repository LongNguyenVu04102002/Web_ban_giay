package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import com.example.datn.service.Impl.MauSacServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
=======
>>>>>>> 7ad8ffbb5f6e88af3108c968d7cd0797e69ce7dd
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
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
<<<<<<< HEAD
//    @GetMapping("/home")
//    public String home(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
//            // Người dùng đã đăng nhập, thêm thông tin vào model
//            String email = auth.getName();
//            KhachHang khachHang = khachHangService.getbyEmail(email);
//            if (khachHang != null) {
//                model.addAttribute("hoTen", khachHang.getHoTen());
//            } else {
//                model.addAttribute("hoTen", null);
//            }
//        }
//    public String home(Model model, Authentication authentication) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            model.addAttribute("isAuthenticated", true);
//            model.addAttribute("username", authentication.getName());
//        } else {
//            model.addAttribute("isAuthenticated", false);
//        }
//        return "user/includes/content/home";
//    }
=======

    @GetMapping("/home")
    public String home() {
        return "user/includes/content/home";
    }
>>>>>>> 7ad8ffbb5f6e88af3108c968d7cd0797e69ce7dd

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
        if (sanPham == null) {
            return "redirect:/error";
        }
        model.addAttribute("sanPham", sanPham);

        // Lấy danh sách kích thước duy nhất cho sản phẩm
        List<KichThuoc> uniqueSizes = sanPham.getSanPhamChiTietList().stream()
                .map(SanPhamChiTiet::getKichThuoc)
                .distinct()
                .sorted(Comparator.comparingInt(kt -> Integer.parseInt(kt.getTen())))
                .collect(Collectors.toList());

        model.addAttribute("uniqueSizes", uniqueSizes);

        // Lấy danh sách màu sắc duy nhất cho sản phẩm
        List<MauSac> uniqueColors = sanPham.getSanPhamChiTietList().stream()
                .map(SanPhamChiTiet::getMauSac)
                .distinct()
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

    @GetMapping("/success")
    public String success() {
        return "user/includes/content/ordersusses";
    }

    @GetMapping("/login")
    public String login() {
        return "a";
    }

}
