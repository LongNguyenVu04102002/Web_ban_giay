package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import com.example.datn.service.impl.KichThuocServiceImpl;
import com.example.datn.service.impl.MauSacServiceImpl;
import com.example.datn.service.impl.SanPhamServiceImpl;
import com.example.datn.service.impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private KhachHangServiceImpl khachHangService;
@GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // Lấy email của người dùng đã đăng nhập
            KhachHang khachHang = khachHangService.getByEmail(email); // Tìm thông tin khách hàng từ database
            model.addAttribute("khachHang", khachHang);
        }
        return "user/includes/content/home";
    }
    public String home() {
        return "user/includes/content/home";
    }



    @GetMapping("/")
    public String defaultPage() {
        return "user/includes/content/home";
    }
    @GetMapping("/shop")
    public String shop(Model model,Authentication authentication) {
        List<SanPham> sanPhamList = sanPhamService.getAll();
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("kichThuocList", kichThuocList);
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // Lấy email của người dùng đã đăng nhập
            KhachHang khachHang = khachHangService.getByEmail(email); // Tìm thông tin khách hàng từ database
            model.addAttribute("khachHang", khachHang);

        } return "user/includes/content/shop";
    }
    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id, Model model,Authentication authentication) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            // Xử lý khi không tìm thấy sản phẩm, ví dụ: chuyển hướng đến trang lỗi
            return "redirect:/error"; // Thay thế bằng trang lỗi phù hợp
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

    @GetMapping("/checkout")
    public String checkout() {
        return "user/includes/content/checkout";
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/includes/content/cart";
    }

}
