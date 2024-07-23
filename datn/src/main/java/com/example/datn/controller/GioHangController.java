package com.example.datn.controller;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.service.GioHangService;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/giohang")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private SanPhamService sanPhamService; // Dịch vụ để lấy danh sách sản phẩm

    @GetMapping("/add-product")
    public String showAddProductForm(Model model, @RequestParam Long khachHangId) {
        List<SanPham> sanPhamList = sanPhamService.getAll(); // Lấy danh sách sản phẩm
        model.addAttribute("khachHangId", khachHangId);
        model.addAttribute("sanPhamList", sanPhamList);
        return "user/includes/content/add-to-cart";
    }

    @PostMapping("/add-product")
    public String addProductToCart(@RequestParam Long khachHangId,
                                   @RequestParam Long sanPhamChiTietId,
                                   @RequestParam Integer soLuong,
                                   Model model) {
        GioHang gioHang = gioHangService.addProductToCart(khachHangId, sanPhamChiTietId, soLuong);
        model.addAttribute("gioHang", gioHang);
        return "redirect:/giohang/view-cart?khachHangId=" + khachHangId;
    }

    @GetMapping("/cart/view")
    public String viewCart(@RequestParam Long khachHangId, Model model) {
        GioHang gioHang = gioHangService.findCartByCustomerId(khachHangId);
        model.addAttribute("gioHang", gioHang);
        return "user/includes/content/view-cart";
    }
}
