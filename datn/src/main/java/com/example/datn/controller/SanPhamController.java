package com.example.datn.controller;

import com.example.datn.entity.SanPham;
import com.example.datn.service.Impl.ChatLieuServiceImpl;
import com.example.datn.service.Impl.CoGiayServiceImpl;
import com.example.datn.service.Impl.DayGiayServiceImpl;
import com.example.datn.service.Impl.DeGiayServiceImpl;
import com.example.datn.service.Impl.LotGiayServiceImpl;
import com.example.datn.service.Impl.MuiGiayServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private ChatLieuServiceImpl chatLieuService;

    @Autowired
    private CoGiayServiceImpl coGiayService;

    @Autowired
    private DayGiayServiceImpl dayGiayService;

    @Autowired
    private DeGiayServiceImpl deGiayService;

    @Autowired
    private LotGiayServiceImpl lotGiayService;

    @Autowired
    private MuiGiayServiceImpl muiGiayService;

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @GetMapping("/giay")
    public String show(Model model) {
        List<SanPham> sanPhamList = sanPhamService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        return "admin/includes/content/sanpham/giay/home";
    }

    @GetMapping("/giay/form")
    public String form(Model model) {
        model.addAttribute("sanPham", new SanPham());
        return getString(model);
    }

    private String getString(Model model) {
        model.addAttribute("lstDeGiay", deGiayService.getDeGiaysByTrangThai(false));
        model.addAttribute("lstThuongHieu", thuongHieuService.getThuongHieusByTrangThai(false));
        model.addAttribute("lstCoGiay", coGiayService.getCoGiaysByTrangThai(false));
        model.addAttribute("lstLotGiay", lotGiayService.getLotGiaysByTrangThai(false));
        model.addAttribute("lstMuiGiay", muiGiayService.getMuiGiaysByTrangThai(false));
        model.addAttribute("lstChatLieu", chatLieuService.getChatLieusByTrangThai(false));
        model.addAttribute("lstDayGiay", dayGiayService.getDayGiaysByTrangThai(false));
        return "admin/includes/content/sanpham/giay/form";
    }

    @GetMapping("/giay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return getString(model);
    }

    @PostMapping("/giay/save")
    public String save(SanPham sanPham) {
        sanPhamService.save(sanPham);
        return "redirect:/admin/sanpham/giay";
    }

    @PostMapping("/giay/update/{id}")
    public String update(@PathVariable Long id) {
        sanPhamService.update(id);
        return "redirect:/admin/sanpham/giay";
    }

}


