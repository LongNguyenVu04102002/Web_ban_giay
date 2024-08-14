package com.example.datn.controller;

import com.example.datn.dto.CartItem;
import com.example.datn.dto.MyUserDetail;
import com.example.datn.entity.HoaDon;
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
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import com.example.datn.service.Impl.TimeLineServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class HomeController {

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

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private TimeLineServiceImpl timeLineService;

    private final AtomicLong counter = new AtomicLong();

    @ModelAttribute("cartItems")
    public List<CartItem> cartItems() {
        return new ArrayList<>();
    }

    // Home
    @GetMapping("/home")
    public String home() {
        return "user/includes/content/home";
    }

    // Shop
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

    // About
    @GetMapping("/about")
    public String about() {
        return "user/includes/content/about";
    }

    // Blog
    @GetMapping("/blog")
    public String blog() {
        return "user/includes/content/blog";
    }

    // Contact
    @GetMapping("/contact")
    public String contact() {
        return "user/includes/content/contact";
    }

    // Account
    @GetMapping("/account")
    private String account() {
        return "user/includes/content/user/account";
    }

    // Invoice
    @GetMapping("/invoice")
    private String invoice(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof MyUserDetail userDetails) {
                if (userDetails.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
                    List<HoaDon> hoaDonList = hoaDonService.getHoaDonKhachHang(userDetails.getId());
                    model.addAttribute("hoaDonList", hoaDonList);
                }
            }
        }
        return "user/includes/content/user/invoice";
    }

    @GetMapping("/user/invoice/detail/{id}")
    public String getHoaDonById(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        model.addAttribute("hoaDon", hoaDon);
        return "user/includes/content/user/detail";
    }

    @PostMapping("/user/invoice/update/thongtingiaohang")
    public String updateThongTinGiaoHang(@ModelAttribute HoaDon hoaDon, RedirectAttributes redirectAttributes) {
        hoaDonService.updateThongTinGiaoHang(hoaDon);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/user/invoice/detail/" + hoaDon.getHoaDonId();
    }

    @GetMapping("/user/invoice/cartdetail/{id}")
    public String getCartDetail(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.getAll();
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "user/includes/content/user/cartdetail";
    }

    @PostMapping("/user/invoice/update")
    public String update(@RequestParam Long idHoaDon, @RequestParam Long idSanPhamChiTiet, RedirectAttributes redirectAttributes) {
        hoaDonService.update(idHoaDon, idSanPhamChiTiet);
        redirectAttributes.addFlashAttribute("add", true);
        return "redirect:/user/invoice/cartdetail/" + idHoaDon;
    }

    @PostMapping("/user/invoice/update/stepdown")
    public String stepDown(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.stepDown(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("stepdown", true);
        return "redirect:/user/invoice/cartdetail/" + hoaDonId;
    }

    @PostMapping("/user/invoice/update/stepup")
    public String stepUp(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.stepUp(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("stepup", true);
        return "redirect:/user/invoice/cartdetail/" + hoaDonId;
    }

    @PostMapping("/user/invoice/delete")
    public String delete(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.delete(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("deletes", true);
        return "redirect:/user/invoice/cartdetail/" + hoaDonId;
    }

    @PostMapping("/user/timeline/huydon/{id}")
    public String huyDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) {
        timeLineService.huyDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/user/invoice/detail/" + id;
    }

    // Cart
    @GetMapping("/cart")
    public String cart(Model model) {
        if (!model.containsAttribute("pgg")) {
            model.addAttribute("pgg", new PhieuGiamGiaResponse());
        }
        return "user/includes/content/cart";
    }


    @PostMapping("/cart/add")
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartItem cartItem, @ModelAttribute("cartItems") List<CartItem> cartItems) {
        Map<String, Object> response = new HashMap<>();
        boolean itemExists = false;

        try {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findByNameAndSizeAndColor(cartItem.getTenSanPham(), cartItem.getKichThuoc(), cartItem.getMauSac());
            int maxQuantity = sanPhamChiTiet.getSoLuong();

            for (CartItem item : cartItems) {
                if (item.getTenSanPham().equals(cartItem.getTenSanPham())
                        && item.getMauSac().equals(cartItem.getMauSac())
                        && item.getKichThuoc().equals(cartItem.getKichThuoc())) {
                    int newQuantity = item.getSoLuong() + cartItem.getSoLuong();
                    if (newQuantity > maxQuantity) {
                        response.put("status", "error");
                        return ResponseEntity.badRequest().body(response);
                    }
                    item.setSoLuong(newQuantity);
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                if (cartItem.getSoLuong() > maxQuantity) {
                    response.put("status", "error");
                    return ResponseEntity.badRequest().body(response);
                }
                cartItem.setId(counter.incrementAndGet());
                cartItems.add(cartItem);
            }

            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/cart/update/stepup")
    public String stepUp(@RequestParam("id") Long id, @ModelAttribute("cartItems") List<CartItem> cartItems, RedirectAttributes redirectAttributes) {
        boolean updated = false;

        for (CartItem item : cartItems) {
            if (item.getId().equals(id)) {
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findByNameAndSizeAndColor(item.getTenSanPham(), item.getKichThuoc(), item.getMauSac());
                if (sanPhamChiTiet.getSoLuong() > item.getSoLuong()) {
                    item.setSoLuong(item.getSoLuong() + 1);
                    updated = true;
                } else {
                    redirectAttributes.addFlashAttribute("quantity", true);
                    return "redirect:/cart";
                }
                break;
            }
        }

        if (updated) {
            redirectAttributes.addFlashAttribute("stepup", true);
        }
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
        redirectAttributes.addFlashAttribute("stepdown", true);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable("id") Long id, @ModelAttribute("cartItems") List<CartItem> cartItems, RedirectAttributes redirectAttributes) {
        cartItems.removeIf(item -> item.getId().equals(id));
        redirectAttributes.addFlashAttribute("remote", true);
        return "redirect:/cart";
    }

    // Checkout
    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        PhieuGiamGiaResponse pgg = (PhieuGiamGiaResponse) session.getAttribute("pgg");
        if (pgg != null) {
            model.addAttribute("pgg", pgg);
        }
        model.addAttribute("thanhToanResponse", new ThanhToanResponse());
        return "user/includes/content/checkout";
    }

    @PostMapping("/checkout/save")
    public String saveHoaDon(@ModelAttribute ThanhToanResponse thanhToanResponse,
                             @ModelAttribute("cartItems") List<CartItem> cartItems,
                             HttpSession session,
                             Model model) {
        PhieuGiamGiaResponse pgg = (PhieuGiamGiaResponse) session.getAttribute("pgg");
        if (pgg == null) {
            pgg = new PhieuGiamGiaResponse();
            pgg.setTienGiam(BigDecimal.ZERO);
        }

        String maVanDon = hoaDonService.saveHoaDonOnline(pgg, thanhToanResponse, cartItems);
        session.removeAttribute("pgg");
        cartItems.clear();
        model.addAttribute("maVanDon", maVanDon);

        return "user/includes/content/ordersusses";
    }


    @GetMapping("/success")
    public String success() {
        return "user/includes/content/ordersusses";
    }

    // Discount
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

}
