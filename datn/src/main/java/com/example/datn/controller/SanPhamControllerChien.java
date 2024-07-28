package com.example.datn.controller;

import com.example.datn.dto.CartDTO;
import com.example.datn.dto.CartItemDTO;
import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.service.KichThuocService;
import com.example.datn.service.MauSacService;
import com.example.datn.service.SanPhamChiTietLongService;
import com.example.datn.service.SanPhamServiceLong;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/shopp")
public class SanPhamControllerChien {
    @Autowired
    private SanPhamServiceLong sanPhamService;

    @Autowired
    private SanPhamChiTietLongService sanPhamChiTietService;

    @Autowired
    private SanPhamChiTietRepository repository;
    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private KichThuocService kichThuocService;

    @GetMapping
    public String listProducts(Model model) {
        // Lấy toàn bộ danh sách chi tiết sản phẩm
        List<SanPhamChiTiet> productDetails = repository.findAll();

        // Sử dụng TreeMap để tự động sắp xếp theo tên sản phẩm
        Map<String, List<SanPhamChiTiet>> categorizedProductDetails = new TreeMap<>();

        // Phân loại chi tiết sản phẩm theo tên sản phẩm
        for (SanPhamChiTiet productDetail : productDetails) {
            // Giả sử productDetail.getSanPham().getTen() trả về tên sản phẩm
            categorizedProductDetails
                    .computeIfAbsent(productDetail.getSanPham().getTen(), k -> new ArrayList<>())
                    .add(productDetail);
        }

        // Tạo danh sách chi tiết sản phẩm đầu tiên từ mỗi danh mục
        List<SanPhamChiTiet> firstProductDetails = new ArrayList<>();
        for (List<SanPhamChiTiet> productDetailList : categorizedProductDetails.values()) {
            if (!productDetailList.isEmpty()) {
                firstProductDetails.add(productDetailList.get(0)); // Lấy chi tiết sản phẩm đầu tiên
            }
        }

        model.addAttribute("productList", firstProductDetails);
        return "user/includes/content/shop-chien";
    }


    @GetMapping("/{sanPhamId}")
    public String productDetails(@PathVariable Long sanPhamId, Model model) {
        SanPham sanPham = sanPhamService.findById(sanPhamId);
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.findBySanPhamId(sanPhamId);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "user/includes/content/detail-test";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long sanPhamChiTietId, @RequestParam Integer soLuong, HttpSession session) {
        // Kiểm tra số lượng phải lớn hơn 0
        if (soLuong <= 0) {
            // Xử lý khi số lượng không hợp lệ
            return "redirect:/error"; // Hoặc trang xử lý lỗi khác
        }

        CartDTO cart = (CartDTO) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartDTO();
            session.setAttribute("cart", cart);
        }

        // Tìm sản phẩm chi tiết
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findById(sanPhamChiTietId);
        if (sanPhamChiTiet == null) {
            // Xử lý khi sản phẩm không tồn tại
            return "redirect:/error"; // Hoặc trang xử lý lỗi khác
        }

        // Tạo đối tượng CartItemDTO
        CartItemDTO item = new CartItemDTO(
                sanPhamChiTietId,
                sanPhamChiTiet.getSanPham().getTen(),
                sanPhamChiTiet.getGiaBan(),
                soLuong,
                sanPhamChiTiet.getGiaBan().multiply(new BigDecimal(soLuong)),
                sanPhamChiTiet.getMauSac().getMauSacId(),
                sanPhamChiTiet.getMauSac().getTen(),
                sanPhamChiTiet.getKichThuoc().getKichThuocId(),
                sanPhamChiTiet.getKichThuoc().getTen()
        );

        // Thêm sản phẩm vào giỏ hàng
        cart.addItem(item);
        return "redirect:/cartOn";
    }

}
