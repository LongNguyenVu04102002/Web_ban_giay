package com.example.datn.controller;

import com.example.datn.dto.CartItem;
import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import com.example.datn.service.Impl.MauSacServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("cartItems")
public class BanHangOnlineController {

    @Autowired
    private HoaDonServiceImpl hoaDonService;
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    private final AtomicLong counter = new AtomicLong();

    @ModelAttribute("cartItems")
    public List<CartItem> cartItems() {
        return new ArrayList<>();
    }

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
        return "user/includes/content/login";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "user/includes/content/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("thanhToanResponse", new ThanhToanResponse());
        return "user/includes/content/checkout";
    }


    @PostMapping("/cart/add")
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartItem cartItem, @ModelAttribute("cartItems") List<CartItem> cartItems) {
        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getTenSanPham().equals(cartItem.getTenSanPham())
                    && item.getMauSac().equals(cartItem.getMauSac())
                    && item.getKichThuoc().equals(cartItem.getKichThuoc())) {
                item.setSoLuong(item.getSoLuong() + cartItem.getSoLuong());
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            cartItem.setId(counter.incrementAndGet());
            cartItems.add(cartItem);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cart/update/stepup")
    public String stepUp(@RequestParam("id") Long id, @ModelAttribute("cartItems") List<CartItem> cartItems, RedirectAttributes redirectAttributes) {
        for (CartItem item : cartItems) {
            if (item.getId().equals(id)) {
                item.setSoLuong(item.getSoLuong() + 1);
                break;
            }
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update/stepdown")
    public String stepDown(@RequestParam("id") Long id, @ModelAttribute("cartItems") List<CartItem> cartItems, RedirectAttributes redirectAttributes) {
        for (CartItem item : cartItems) {
            if (item.getId().equals(id) && item.getSoLuong() > 1) {
                item.setSoLuong(item.getSoLuong() - 1);
                break;
            }
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable("id") Long id, @ModelAttribute("cartItems") List<CartItem> cartItems, RedirectAttributes redirectAttributes) {
        cartItems.removeIf(item -> item.getId().equals(id));
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/cart";
    }

    @PostMapping("/checkout/save")
    public String saveHoaDon(@ModelAttribute ThanhToanResponse thanhToanResponse,
                             @ModelAttribute("cartItems") List<CartItem> cartItems,
                             Model model) {
        String maVanDon = hoaDonService.saveHoaDonOnline(thanhToanResponse, cartItems);
        cartItems.clear();
        model.addAttribute("maVanDon", maVanDon);
        return "user/includes/content/ordersusses";
    }

}
