package com.example.datn.controller;

import com.example.datn.entity.GioHang;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        BigDecimal tongTienGiam = (BigDecimal) model.asMap().getOrDefault("tongTienGiam", BigDecimal.ZERO);

        model.addAttribute("gioHangList", gioHangList);
        model.addAttribute("tongTienGiam", tongTienGiam);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietService.getAll());
        model.addAttribute("khachHangList", khachHangService.getAll());

        return "/admin/includes/content/banhang/home";
    }

    @PostMapping("/banhang/addToCart")
    private String addToCart(@RequestParam Long gioHangId, @RequestParam Long sanPhamChiTietId) {
        gioHangService.addToCart(gioHangId, sanPhamChiTietId);
        return "redirect:/admin/banhang";
    }

    @GetMapping("/banhang/update/stepdown")
    public String stepDown(@RequestParam("gioHangChiTietId") Long gioHangChiTietId,
                           @RequestParam("sanPhamChiTietId") Long sanPhamChiTietId) {
        gioHangService.stepDown(gioHangChiTietId, sanPhamChiTietId);
        return "redirect:/admin/banhang";
    }

    @GetMapping("/banhang/update/stepup")
    public String stepUp(@RequestParam("gioHangChiTietId") Long gioHangChiTietId,
                         @RequestParam("sanPhamChiTietId") Long sanPhamChiTietId) {
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
                       @RequestParam(defaultValue = "0") BigDecimal totalAmount,
                       @RequestParam Long thanhToan) {
        System.out.println(khachHangId);
//        hoaDonService.save(gioHangId, khachHangId, discountCode, discountAmount, totalAmount, thanhToan);
        return "redirect:/admin/hoadon";
    }

}
