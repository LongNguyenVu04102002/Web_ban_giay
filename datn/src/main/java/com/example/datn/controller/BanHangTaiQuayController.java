package com.example.datn.controller;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.service.Impl.GioHangServiceImpl;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BanHangTaiQuayController {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private GioHangServiceImpl gioHangService;

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @GetMapping("/banhang")
    private String view(Model model) {
        return GioHangList(model);
    }

    private String GioHangList(Model model) {
        List<GioHang> gioHangList = Arrays.asList(
                gioHangService.getById(1L),
                gioHangService.getById(2L),
                gioHangService.getById(3L),
                gioHangService.getById(4L),
                gioHangService.getById(5L)
        );

        BigDecimal tamTinh = gioHangList.stream()
                .flatMap(gioHang -> gioHang.getGioHangChiTietList().stream())
                .map(chiTiet -> {
                    BigDecimal giaBan = chiTiet.getSanPhamChiTiet().getGiaBan();
                    BigDecimal soLuong = BigDecimal.valueOf(chiTiet.getSoLuong());
                    return soLuong.multiply(giaBan);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal tongTienGiam = (BigDecimal) model.asMap().getOrDefault("tongTienGiam", BigDecimal.ZERO);
        BigDecimal tongTienSauGiam = tamTinh.subtract(tongTienGiam);

        model.addAttribute("gioHangList", gioHangList);
        model.addAttribute("tamTinh", tamTinh);
        model.addAttribute("tongTienGiam", tongTienGiam);
        model.addAttribute("tongTienSauGiam", tongTienSauGiam);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietService.getAll());
        model.addAttribute("khachHangList", khachHangService.getAll());

        return "/admin/includes/content/banhang/home";
    }

    @PostMapping("/banhang/addToCart")
    private String addToCart(@RequestParam Long gioHangId, @RequestParam Long sanPhamChiTietId) {
        gioHangService.addToCart(gioHangId, sanPhamChiTietId);
        return "redirect:/admin/banhang";
    }

    @PostMapping("/banhang/update/stepdown")
    public String stepDown(@RequestParam Long gioHangChiTietId, @RequestParam Long sanPhamChiTietId) {
        gioHangService.stepDown(gioHangChiTietId, sanPhamChiTietId);
        return "redirect:/admin/banhang";
    }

    @PostMapping("/banhang/update/stepup")
    public String stepUp(@RequestParam Long gioHangChiTietId, @RequestParam Long sanPhamChiTietId) {
        gioHangService.stepUp(gioHangChiTietId, sanPhamChiTietId);
        return "redirect:/admin/banhang";
    }

    @GetMapping("/banhang/delete/{id}")
    public String delete(@PathVariable Long id) {
        gioHangService.delete(id);
        return "redirect:/admin/banhang";
    }

    @PostMapping("/banhang/pgg")
    public String pgg(@RequestParam String discountCode, @RequestParam Long gioHangId, Model model) {
        BigDecimal tienGiam = gioHangService.pgg(discountCode, gioHangId);
        model.addAttribute("tongTienGiam", tienGiam);
        model.addAttribute("discountCode", discountCode);
        return GioHangList(model);
    }

    @PostMapping("/banhang/save")
    public String save(@RequestParam Long gioHangId,
                       @RequestParam(defaultValue = "0") Long khachHangId,
                       @RequestParam(defaultValue = "") String discountCode,
                       @RequestParam(defaultValue = "0") BigDecimal discountAmount,
                       @RequestParam BigDecimal totalAmount,
                       @RequestParam Long thanhToan) {
        hoaDonService.save(gioHangId, khachHangId, discountCode, discountAmount, totalAmount, thanhToan);
        return "redirect:/admin/hoadon";
    }

}
