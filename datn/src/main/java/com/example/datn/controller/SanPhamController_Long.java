package com.example.datn.controller;
import com.example.datn.dto.CartDTO;
import com.example.datn.dto.CartItemDTO;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.service.SanPhamChiTietLongService;
import com.example.datn.service.SanPhamChiTietService;
import com.example.datn.service.SanPhamService;
import com.example.datn.service.SanPhamServiceLong;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController_Long {

    @Autowired
    private SanPhamServiceLong sanPhamService;

    @Autowired
    private SanPhamChiTietLongService sanPhamChiTietService;

    @GetMapping
    public String listProducts(Model model) {
        List<SanPham> products = sanPhamService.findAll();
        model.addAttribute("products", products);
        return "user/includes/content/sp-list";
    }

    @GetMapping("/{sanPhamId}")
    public String productDetails(@PathVariable Long sanPhamId, Model model) {
        SanPham sanPham = sanPhamService.findById(sanPhamId);
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.findBySanPhamId(sanPhamId);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "user/includes/content/sp-ct";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long sanPhamChiTietId, @RequestParam Integer soLuong, HttpSession session) {
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartDTO();
            session.setAttribute("cart", cart);
        }
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findById(sanPhamChiTietId);
        CartItemDTO item = new CartItemDTO(sanPhamChiTietId, sanPhamChiTiet.getSanPham().getTen(), sanPhamChiTiet.getGiaBan(), soLuong, sanPhamChiTiet.getGiaBan().multiply(new BigDecimal(soLuong)));
        cart.addItem(item);
        return "redirect:/cartOn";
    }
}
