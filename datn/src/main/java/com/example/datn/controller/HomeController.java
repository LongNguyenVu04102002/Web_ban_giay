package com.example.datn.controller;

import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.service.impl.KichThuocServiceImpl;
import com.example.datn.service.impl.SanPhamServiceImpl;
import com.example.datn.service.impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/home")
    public String home(){
        return "user/includes/content/home";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<SanPham> sanPhamList = sanPhamService.getAll();
        List<ThuongHieu> thuongHieuList = thuongHieuService.getAll();
        List<KichThuoc> kichThuocList = kichThuocService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("thuongHieuList", thuongHieuList);
        model.addAttribute("kichThuocList", kichThuocList);
        return "user/includes/content/shop";
    }

    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);

//        SanPhamChiTiet sanPhamChiTiet = chiTietService.getSanPhamById(id);
        return "user/includes/content/detail";
    }
    @GetMapping("/about")
    public String about(){
        return "user/includes/content/about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "user/includes/content/blog";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/includes/content/contact";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "user/includes/content/checkout";
    }

    @GetMapping("/cart")
    public String cart(){
        return "user/includes/content/cart";
    }

}
