package com.example.datn.controller;

import com.example.datn.dto.CartItem;
import com.example.datn.dto.SanPhamHomeDTO;
import com.example.datn.entity.*;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import com.example.datn.model.response.ThanhToanResponse;
import com.example.datn.service.Impl.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;
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
    @Autowired
    private MauSacServiceImpl mauSacServiceImpl;

    @ModelAttribute("cartItems")
    public List<CartItem> cartItems() {
        return new ArrayList<>();
    }

    // Home
    @GetMapping("/home")
    public String home(Model model) {

        Page<SanPhamHomeDTO> sanPhams = sanPhamService.getSanPhamForHomePage(PageRequest.of(0, 8));
        List<SanPhamHomeDTO> sanPhamTop = sanPhamService.getTopSellingProduct();
        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("sanPhamTop", sanPhamTop);
        return "user/includes/content/home";
    }


    @GetMapping("/home/detail/{id}")
    public String detailhome(@PathVariable Long id, Model model) {
        return getString(id, model);
    }

    @GetMapping("/shop")
    public String getShopPage(
            @RequestParam(required = false) Long thuongHieuId,
            @RequestParam(required = false) Long kichThuocId,
            @RequestParam(required = false) Long mauSacId,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 100, page = 0, sort = "ten", direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {

        Page<SanPhamHomeDTO> page = sanPhamService.getSanPhamForShopPage(thuongHieuId, kichThuocId, mauSacId, keyword, pageable);

        page.getContent();
        if (page.getContent().isEmpty()) {
            model.addAttribute("noProducts", true);
            page = sanPhamService.getSanPhamForShopPage(null, null, null, null, pageable);
        }
        model.addAttribute("products", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("thuongHieuId", thuongHieuId);
        model.addAttribute("kichThuocId", kichThuocId);
        model.addAttribute("mauSacId", mauSacId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("kichThuocList", kichThuocService.getAll());
        model.addAttribute("mauSacList", mauSacServiceImpl.getAll());
        model.addAttribute("thuongHieuList", thuongHieuService.getAll());

        return "user/includes/content/shop";
    }

    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        return getString(id, model);
    }

    private String getString(@PathVariable Long id, Model model) {
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

        Map<Long, List<String>> productImg = new HashMap<>();
        for (SanPhamChiTiet chiTiet : sanPham.getSanPhamChiTietList()) {
            List<String> hinhAnh = chiTiet.getLstAnh().stream()
                    .map(hinhAnhEntity -> {
                        if (hinhAnhEntity.getDataImg() != null) {
                            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(hinhAnhEntity.getDataImg());
                        }
                        return hinhAnhEntity.getLink();
                    })
                    .distinct()
                    .collect(Collectors.toList());
            productImg.put(chiTiet.getSanPhamChiTietId(), hinhAnh);
        }
        model.addAttribute("productImg", productImg);

        model.addAttribute("sanPhamChiTietList", sanPham.getSanPhamChiTietList());

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
    @GetMapping("/account/{id}")
    private String account(@PathVariable Long id) {
        return "user/includes/content/user/account";
    }

    // Invoice
    @GetMapping("/invoice/{id}")
    private String invoice(@PathVariable Long id, Model model) {
        List<HoaDon> hoaDonList = hoaDonService.getHoaDonKhachHang(id);
        model.addAttribute("hoaDonList", hoaDonList);
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
        boolean add = hoaDonService.update(idHoaDon, idSanPhamChiTiet);
        if (add) {
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("info", true);
        }
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
        boolean stepUp = hoaDonService.stepUp(hoaDonId, hoaDonChiTietId);
        if (stepUp){
            redirectAttributes.addFlashAttribute("stepup", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/user/invoice/cartdetail/" + hoaDonId;
    }

    @PostMapping("/user/invoice/delete")
    public String delete(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.delete(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("deletes", true);
        return "redirect:/user/invoice/cartdetail/" + hoaDonId;
    }

    @PostMapping("/user/timeline/huydon/{id}")
    public String huyDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
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
    public String saveHoaDon(@RequestParam Long khachHangId,
                             @ModelAttribute ThanhToanResponse thanhToanResponse,
                             @ModelAttribute("cartItems") List<CartItem> cartItems,
                             HttpSession session,
                             Model model) throws MessagingException {
        PhieuGiamGiaResponse pgg = (PhieuGiamGiaResponse) session.getAttribute("pgg");
        if (pgg == null) {
            pgg = new PhieuGiamGiaResponse();
            pgg.setTienGiam(BigDecimal.ZERO);
        }

        String maVanDon = hoaDonService.saveHoaDonOnline(khachHangId, pgg, thanhToanResponse, cartItems);
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

    // Tra cuu hoa don
    @GetMapping("/viewSearchInvoice")
    private String viewSearchInvoice() {
        return "user/includes/content/search/search";
    }

    @RequestMapping(value = "/searchInvoice", method = {RequestMethod.GET, RequestMethod.POST})
    private String searchInvoice(@RequestParam String maVanDon,
                                 @RequestParam String email,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        HoaDon hoaDon = hoaDonService.getHoaDonTraCuu(maVanDon, email);
        if (hoaDon != null) {
            List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.findAllByTrangThaiTrue();
            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("maVanDon", maVanDon);
            model.addAttribute("email", email);
            model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
            model.addAttribute("success", true);
            return "user/includes/content/search/detail";
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/viewSearchInvoice";
        }

    }

    @PostMapping("/search/timeline/huydon/{id}")
    public String huyDonHangSearch(@PathVariable Long id,
                                   @RequestParam String mota,
                                   @RequestParam String maVanDon,
                                   @RequestParam String email,
                                   RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.huyDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/searchInvoice?maVanDon=" + maVanDon + "&email=" + email;
    }

    @PostMapping("/search/invoice/update/thongtingiaohang")
    public String updateThongTinGiaoHangSearch(@ModelAttribute HoaDon hoaDon,
                                               @RequestParam String maVanDon,
                                               @RequestParam String email,
                                               RedirectAttributes redirectAttributes) {
        hoaDonService.updateThongTinGiaoHang(hoaDon);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/searchInvoice?maVanDon=" + maVanDon + "&email=" + email;
    }

    @GetMapping("/search/invoice/cartdetail/{id}")
    public String getCartDetailSearch(@PathVariable Long id,
                                      @RequestParam String maVanDon,
                                      @RequestParam String email,
                                      Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.findAllByTrangThaiTrue();
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("maVanDon", maVanDon);
        model.addAttribute("email", email);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "user/includes/content/search/cartdetail";
    }

    @PostMapping("/search/invoice/update")
    public String updateSearch(@RequestParam Long idHoaDon,
                               @RequestParam Long idSanPhamChiTiet,
                               @RequestParam String maVanDon,
                               @RequestParam String email,
                               RedirectAttributes redirectAttributes) {
        boolean add = hoaDonService.update(idHoaDon, idSanPhamChiTiet);
        if (add) {
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/search/invoice/cartdetail/" + idHoaDon + "?maVanDon=" + maVanDon + "&email=" + email;
    }

    @PostMapping("/search/invoice/update/stepdown")
    public String stepDownSearch(@RequestParam Long hoaDonId,
                                 @RequestParam Long hoaDonChiTietId,
                                 @RequestParam String maVanDon,
                                 @RequestParam String email,
                                 RedirectAttributes redirectAttributes) {
        hoaDonService.stepDown(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("stepdown", true);
        return "redirect:/search/invoice/cartdetail/" + hoaDonId + "?maVanDon=" + maVanDon + "&email=" + email;

    }

    @PostMapping("/search/invoice/update/stepup")
    public String stepUpSearch(@RequestParam Long hoaDonId,
                               @RequestParam Long hoaDonChiTietId,
                               @RequestParam String maVanDon,
                               @RequestParam String email,
                               RedirectAttributes redirectAttributes) {
        boolean stepup = hoaDonService.stepUp(hoaDonId, hoaDonChiTietId);
        if (stepup) {
            redirectAttributes.addFlashAttribute("stepup", true);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/search/invoice/cartdetail/" + hoaDonId + "?maVanDon=" + maVanDon + "&email=" + email;
    }

    @PostMapping("/search/invoice/delete")
    public String deleteSearch(@RequestParam Long hoaDonId,
                               @RequestParam Long hoaDonChiTietId,
                               @RequestParam String maVanDon,
                               @RequestParam String email,
                               RedirectAttributes redirectAttributes) {
        hoaDonService.delete(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("deletes", true);
        return "redirect:/search/invoice/cartdetail/" + hoaDonId + "?maVanDon=" + maVanDon + "&email=" + email;

    }

}
