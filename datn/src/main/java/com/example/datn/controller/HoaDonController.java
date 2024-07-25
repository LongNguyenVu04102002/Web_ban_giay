package com.example.datn.controller;

import com.example.datn.entity.HoaDon;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.NhanVienServiceImpl;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HoaDonController {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    NhanVienServiceImpl nhanVienService;

    @GetMapping("/hoadon")
    public String hoaDon(Model model) {
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();
        List<HoaDon> hoaDonChoXacNhan = hoaDonService.getHoaDonChoXacNhan();
        List<HoaDon> hoaDonDaXacNhan = hoaDonService.getHoaDonDaXacNhan();
        List<HoaDon> hoaDonChoGiaoHang = hoaDonService.getHoaDonChoGiaoHang();
        List<HoaDon> hoaDonDangGiaoHang = hoaDonService.getHoaDonDangGiaoHang();
        List<HoaDon> hoaDonDaGiaoHang = hoaDonService.getHoaDonDaGiaoHang();
        List<HoaDon> hoaDonHoanThanh = hoaDonService.getHoaDonHoanThanh();
        List<HoaDon> hoaDonHuy = hoaDonService.getHoaDonHuy();
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        model.addAttribute("hoaDonList", hoaDonList);
        model.addAttribute("hoaDonChoXacNhan", hoaDonChoXacNhan);
        model.addAttribute("hoaDonDaXacNhan", hoaDonDaXacNhan);
        model.addAttribute("hoaDonChoGiaoHang", hoaDonChoGiaoHang);
        model.addAttribute("hoaDonDangGiaoHang",hoaDonDangGiaoHang);
        model.addAttribute("hoaDonDaGiaoHang", hoaDonDaGiaoHang);
        model.addAttribute("hoaDonHoanThanh", hoaDonHoanThanh);
        model.addAttribute("hoaDonHuy",hoaDonHuy);
        model.addAttribute("nhanVien", nhanViens);
        return "admin/includes/content/hoadon/hoadon";
    }

    @GetMapping("/hoadon/detail/{id}")
    public String getHoaDonById(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/includes/content/hoadon/hoadonchitiet";
    }

    @GetMapping("/hoadon/invoice/{id}")
    public String getInvoice(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/includes/content/hoadon/invoice";
    }

    @GetMapping("/hoadon/cartdetail/{id}")
    public String getCartDetail(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.getAll();
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "admin/includes/content/hoadon/cartdetail";
    }

}
