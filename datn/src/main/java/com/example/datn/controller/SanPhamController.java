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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        SanPham sp = new SanPham();
        sp.setTrangThai(true);
        model.addAttribute("sanPham", sp);
        return getString(model);
    }

    private String getString(Model model) {
        model.addAttribute("lstDeGiay", deGiayService.findAllByTrangThaiTrue());
        model.addAttribute("lstThuongHieu", thuongHieuService.findAllByTrangThaiTrue());
        model.addAttribute("lstCoGiay", coGiayService.findAllByTrangThaiTrue());
        model.addAttribute("lstLotGiay", lotGiayService.findAllByTrangThaiTrue());
        model.addAttribute("lstMuiGiay", muiGiayService.findAllByTrangThaiTrue());
        model.addAttribute("lstChatLieu", chatLieuService.findAllByTrangThaiTrue());
        model.addAttribute("lstDayGiay", dayGiayService.findAllByTrangThaiTrue());
        return "admin/includes/content/sanpham/giay/form";
    }

    @GetMapping("/giay/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return getString(model);
    }

    @PostMapping("/giay/save")
    public String save(SanPham sanPham, RedirectAttributes redirectAttributes) {
        if(sanPham.getSanPhamId() == null){
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("update", true);
        }
        sanPhamService.save(sanPham);
        return "redirect:/admin/sanpham/giay";
    }

    @PostMapping("/giay/update/{id}")
    public String update(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        sanPhamService.update(id);
        redirectAttributes.addFlashAttribute("status",true);
        return "redirect:/admin/sanpham/giay";
    }

}


