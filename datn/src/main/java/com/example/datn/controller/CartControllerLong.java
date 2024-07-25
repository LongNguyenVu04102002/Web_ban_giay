package com.example.datn.controller;
import com.example.datn.dto.CartDTO;
import com.example.datn.dto.CartItemDTO;
import com.example.datn.entity.*;
import com.example.datn.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartOn")
public class CartControllerLong {

    @Autowired
    private SanPhamChiTietLongService sanPhamChiTietService;

    @Autowired
    private HoaDonLongService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private TimeLineService timeLineService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartDTO();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        return "user/includes/content/cartLong";
    }

    @PostMapping("/add")
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



    @PostMapping("/checkoutOn")
    public String checkout(HttpSession session,

                           @RequestParam Long nhanVienId,
                           @RequestParam String hoTen,
                           @RequestParam String sdt,
                           @RequestParam String email,
                           @RequestParam LocalDate ngaySinh
                         ) {
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cartOn";
        }

        NhanVien nhanVien= nhanVienService.getById(nhanVienId);


        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(hoTen);
        khachHang.setSdt(sdt);
        khachHang.setEmail(email);
        khachHang.setNgaySinh(ngaySinh);

        khachHangService.save(khachHang);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setKhachHang(khachHang);
        hoaDon.setTenNguoiNhan(khachHang.getHoTen());
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTongTien(cart.getTongTien());

        hoaDon.setTrangThai(1);

        List<HoaDonChiTiet> hoaDonChiTietList = cart.getItems().stream().map(item -> {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTietService.findById(item.getSanPhamChiTietId()));
            hoaDonChiTiet.setDonGia(item.getGiaBan());
            hoaDonChiTiet.setSoLuong(item.getSoLuong());

            return hoaDonChiTiet;
        }).toList();

        hoaDon.setHoaDonChiTietList(hoaDonChiTietList);
        TimeLine timeLine = new TimeLine();
        timeLine.setHoaDon(hoaDon);
        timeLine.setTrangThai(1);
        timeLine.setNgayTao(LocalDate.now());

        List<TimeLine> timeLineList = new ArrayList<>();
        timeLineList.add(timeLine);

        hoaDon.setTimeLineList(timeLineList);

        hoaDonService.save(hoaDon);

        session.removeAttribute("cart");

        return "redirect:/cartOn/order-success";
    }

    @GetMapping("/order-success")
    public String showOrderSuccessPage(Model model) {
        // Thêm dữ liệu vào model nếu cần

        return "user/includes/content/hoanThanh"; // Tên của template Thymeleaf
    }
}
