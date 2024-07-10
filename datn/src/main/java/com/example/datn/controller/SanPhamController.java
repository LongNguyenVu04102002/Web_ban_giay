//package com.example.datn.controller;
//
//import com.example.datn.entity.SanPham;
//import com.example.datn.entity.SanPhamChiTiet;
//import com.example.datn.service.Impl.SanPhamServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/sanpham")
//public class SanPhamController {
//    @Autowired
//    private SanPhamServiceImpl sanPhamService;
//
//    @GetMapping("/detail/{id}")
//    public String getHoaDonById(@PathVariable Long id, Model model) {
//        SanPham sanPham = sanPhamService.getSanPhamById(id);
//        List<SanPhamChiTiet> uniqueSizes = sanPhamService.uniqueSizes(sanPham.getSanPhamChiTietList());
//        List<SanPhamChiTiet> uniqueColors = sanPhamService.uniqueColor(sanPham.getSanPhamChiTietList());
//        model.addAttribute("selectedSizeId", null);
//        model.addAttribute("selectedColorId", null);
//        model.addAttribute("sanPham", sanPham);
//        model.addAttribute("uniqueSizes", uniqueSizes);
//        model.addAttribute("uniqueColors", uniqueColors);
//        return "user/includes/content/detail";
//
//
//    @GetMapping("/san-pham")
//    public String show(@ModelAttribute("sanPham") SanPham sanPham, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
//        Page<SanPham> page = spService.pagination(p, 10);
//        model.addAttribute("lst", page);
//        model.addAttribute("lstDeGiay", deGiayService.getAll());
//        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("lstCoGiay", coGiayService.getAll());
//        model.addAttribute("lstLotGiay", lotGiayService.getAll());
//        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
//        model.addAttribute("lstChatLieu", chatLieuService.getAll());
//        model.addAttribute("lstDayGiay", dayGiayService.getAll());
//        sanPham.setTrangThai(true);
//        return "left-menu-san-pham";
//    }
//
//    @PostMapping("/san-pham/add")
//    public String add(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
//        if (result.hasErrors()) {
//            Page<SanPham> page = spService.pagination(p, 10);
//            model.addAttribute("lst", page);
//            return "left-menu-san-pham";
//        }
//        model.addAttribute("sanPham", new SanPham());
//        SanPham sp = SanPham.builder()
//                .ten(sanPham.getTen())
//                .namSX(sanPham.getNamSX())
//                .trangThai(sanPham.isTrangThai())
//                .moTa(sanPham.getMoTa())
//                .deGiay(sanPham.getDeGiay())
//                .thuongHieu((sanPham.getThuongHieu()))
//                .coGiay(sanPham.getCoGiay())
//                .lotGiay(sanPham.getLotGiay())
//                .muiGiay(sanPham.getMuiGiay())
//                .chatLieu(sanPham.getChatLieu())
//                .dayGiay(sanPham.getDayGiay())
//                .build();
//        spService.addOrUpdate(sp);
//        Page<SanPham> page = spService.pagination(p, 10);
//        model.addAttribute("lst", page);
//        model.addAttribute("lstDeGiay", deGiayService.getAll());
//        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("lstCoGiay", coGiayService.getAll());
//        model.addAttribute("lstLotGiay", lotGiayService.getAll());
//        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
//        model.addAttribute("lstChatLieu", chatLieuService.getAll());
//        model.addAttribute("lstDayGiay", dayGiayService.getAll());
//        return "redirect:/san-pham";
//    }
//
//    @GetMapping("/san-pham/detail/{id}")
//    public String detail(@ModelAttribute("sanPham") Optional<SanPham> sanPham, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
//        model.addAttribute("sanPham", spService.getOne(id));
//        Page<SanPham> page = spService.pagination(p, 10);
//        model.addAttribute("lst", page);
//        model.addAttribute("lstDeGiay", deGiayService.getAll());
//        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("lstCoGiay", coGiayService.getAll());
//        model.addAttribute("lstLotGiay", lotGiayService.getAll());
//        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
//        model.addAttribute("lstChatLieu", chatLieuService.getAll());
//        model.addAttribute("lstDayGiay", dayGiayService.getAll());
//        return "left-menu-san-pham";
//    }
//
//    @PostMapping("/san-pham/update/{id}")
//    public String update(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
//        if (result.hasErrors()) {
//            Page<SanPham> page = spService.pagination(p, 10);
//            model.addAttribute("lst", page);
//            return "left-menu-san-pham";
//        }
//        model.addAttribute("sanPham", new SanPham());
//        spService.addOrUpdate(sanPham);
//        Page<SanPham> page = spService.pagination(p, 10);
//        model.addAttribute("lst", page);
//        model.addAttribute("lstDeGiay", deGiayService.getAll());
//        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("lstCoGiay", coGiayService.getAll());
//        model.addAttribute("lstLotGiay", lotGiayService.getAll());
//        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
//        model.addAttribute("lstChatLieu", chatLieuService.getAll());
//        model.addAttribute("lstDayGiay", dayGiayService.getAll());
//        return "redirect:/san-pham";
//    }
//
//    @GetMapping("/san-pham/remove/{id}")
//    public String remove(@ModelAttribute("sanPham") SanPham sanPham, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
//        spService.remove(id);
//        Page<SanPham> page = spService.pagination(p, 10);
//        model.addAttribute("lst", page);
//        model.addAttribute("lstDeGiay", deGiayService.getAll());
//        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("lstCoGiay", coGiayService.getAll());
//        model.addAttribute("lstLotGiay", lotGiayService.getAll());
//        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
//        model.addAttribute("lstChatLieu", chatLieuService.getAll());
//        model.addAttribute("lstDayGiay", dayGiayService.getAll());
//        return "redirect:/san-pham";
//    }
//}
