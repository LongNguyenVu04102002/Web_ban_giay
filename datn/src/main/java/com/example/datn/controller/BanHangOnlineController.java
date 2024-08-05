package com.example.datn.controller;

import com.example.datn.dto.CartItem;
<<<<<<< HEAD
import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import com.example.datn.service.Impl.PhieuGiamGiaServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import jakarta.servlet.http.HttpSession;
=======
import com.example.datn.entity.KhachHang;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.KhachHangServiceImpl;
>>>>>>> nv_vinh
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
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
    private KhachHangServiceImpl khachHangService;

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

    @Autowired
    PhieuGiamGiaServiceImpl phieuGiamGiaService;

    private final AtomicLong counter = new AtomicLong();

    @ModelAttribute("cartItems")
    public List<CartItem> cartItems() {
        return new ArrayList<>();
    }

<<<<<<< HEAD
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
=======
    private void addAuthenticationInfo(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            KhachHang khachHang = khachHangService.getAllByEmail(email);
            if (khachHang != null) {
                model.addAttribute("isAuthenticated", true);
                model.addAttribute("username", khachHang.getHoTen());
                model.addAttribute("email", khachHang.getEmail());
            } else {
                model.addAttribute("isAuthenticated", false);
            }
        } else {
            model.addAttribute("isAuthenticated", false);
        }
>>>>>>> nv_vinh
    }

    @GetMapping("/cart")
    public String cart(Model model) {
<<<<<<< HEAD
        if (!model.containsAttribute("pgg")) {
            model.addAttribute("pgg", new PhieuGiamGiaResponse());
        }
=======
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        addAuthenticationInfo(model, authentication);
>>>>>>> nv_vinh
        return "user/includes/content/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        PhieuGiamGiaResponse pgg = (PhieuGiamGiaResponse) session.getAttribute("pgg");
        if (pgg != null) {
            model.addAttribute("pgg", pgg);
        }
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
<<<<<<< HEAD
                             HttpSession session,
                             Model model) {
        PhieuGiamGiaResponse pgg = (PhieuGiamGiaResponse) session.getAttribute("pgg");
        String maVanDon = hoaDonService.saveHoaDonOnline(pgg, thanhToanResponse, cartItems);
        session.removeAttribute("pgg");
        cartItems.clear();
        model.addAttribute("maVanDon", maVanDon);
        return "user/includes/content/ordersusses";
    }

    @PostMapping("/apPhieuGiamGia")
    public String apMaGiamGia(@RequestParam("maGiamGia") String maGiamGia,
                              @ModelAttribute("cartItems") List<CartItem> cartItems,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        PhieuGiamGiaResponse phieuGiamGiaResponse = phieuGiamGiaService.apPhieu(maGiamGia, cartItems);
        if (phieuGiamGiaResponse != null) {
            if (phieuGiamGiaResponse.getTienGiam().compareTo(BigDecimal.ZERO) > 0) {
                session.setAttribute("pgg", phieuGiamGiaResponse);
                redirectAttributes.addFlashAttribute("pgg", phieuGiamGiaResponse);
                redirectAttributes.addFlashAttribute("thanhcong", true);
            } else {
                redirectAttributes.addFlashAttribute("thatbai", true);
            }
        } else {
            redirectAttributes.addFlashAttribute("thatbai", true);
        }
        return "redirect:/cart";
    }

=======
                             Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            KhachHang khachHang = khachHangService.getAllByEmail(email);
            if (khachHang != null) {
                // Lưu thông tin đơn hàng
                String maVanDon = hoaDonService.saveHoaDonOnline(thanhToanResponse, cartItems, khachHang);
                cartItems.clear(); // Xóa giỏ hàng sau khi lưu đơn hàng
                model.addAttribute("maVanDon", maVanDon);
                return "user/includes/content/ordersuccess";
            }
        }
        return "redirect:/login";
    }
>>>>>>> nv_vinh
}
