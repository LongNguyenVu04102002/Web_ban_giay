package com.example.datn.controller;

import com.example.datn.entity.SanPham;
import com.example.datn.service.Impl.DayGiayServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
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
        model.addAttribute("lstDeGiay", deGiayService.getAllDeGiay());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAllCoGiay());
        model.addAttribute("lstLotGiay", lotGiayService.getAllLotGiay());
        model.addAttribute("lstMuiGiay", muiGiayService.getAllMuiGiay());
        model.addAttribute("lstChatLieu", chatLieuService.getAllChatLieu());
        model.addAttribute("lstDayGiay", dayGiayService.getAllDayGiay());
        return "admin/includes/content/sanpham/giay/form";
    }


    @GetMapping("/sanpham/detail/{id}")
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
}
