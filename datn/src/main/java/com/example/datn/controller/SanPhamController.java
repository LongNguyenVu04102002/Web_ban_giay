package com.example.datn.controller;

import com.example.datn.entity.*;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SanPhamController {

    @Autowired
    private IService<SanPham> spService;

    @Autowired
    private IService<DeGiay> deGiayService;

    @Autowired
    private IService<ThuongHieu> thuongHieuService;

    @Autowired
    private IService<CoGiay> coGiayService;

    @Autowired
    private IService<LotGiay> lotGiayService;

    @Autowired
    private IService<MuiGiay> muiGiayService;

    @Autowired
    private IService<ChatLieu> chatLieuService;

    @Autowired
    private IService<DayGiay> dayGiayService;

    @GetMapping("/san-pham")
    public String show(@ModelAttribute("sanPham") SanPham sanPham, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<SanPham> page = spService.pagination(p, 10);
        model.addAttribute("lst", page);
        model.addAttribute("lstDeGiay", deGiayService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAll());
        model.addAttribute("lstLotGiay", lotGiayService.getAll());
        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
        model.addAttribute("lstChatLieu", chatLieuService.getAll());
        model.addAttribute("lstDayGiay", dayGiayService.getAll());
        sanPham.setTrangThai(true);
        return "left-menu-san-pham";
    }

    @PostMapping("/san-pham/add")
    public String add(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<SanPham> page = spService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-san-pham";
        }
        model.addAttribute("sanPham", new SanPham());
        SanPham sp = SanPham.builder()
                .ten(sanPham.getTen())
                .namSX(sanPham.getNamSX())
                .trangThai(sanPham.isTrangThai())
                .moTa(sanPham.getMoTa())
                .deGiay(sanPham.getDeGiay())
                .thuongHieu((sanPham.getThuongHieu()))
                .coGiay(sanPham.getCoGiay())
                .lotGiay(sanPham.getLotGiay())
                .muiGiay(sanPham.getMuiGiay())
                .chatLieu(sanPham.getChatLieu())
                .dayGiay(sanPham.getDayGiay())
                .build();
        spService.addOrUpdate(sp);
        Page<SanPham> page = spService.pagination(p, 10);
        model.addAttribute("lst", page);
        model.addAttribute("lstDeGiay", deGiayService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAll());
        model.addAttribute("lstLotGiay", lotGiayService.getAll());
        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
        model.addAttribute("lstChatLieu", chatLieuService.getAll());
        model.addAttribute("lstDayGiay", dayGiayService.getAll());
        return "redirect:/san-pham";
    }

    @GetMapping("/san-pham/detail/{id}")
    public String detail(@ModelAttribute("sanPham") Optional<SanPham> sanPham, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("sanPham", spService.getOne(id));
        Page<SanPham> page = spService.pagination(p, 10);
        model.addAttribute("lst", page);
        model.addAttribute("lstDeGiay", deGiayService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAll());
        model.addAttribute("lstLotGiay", lotGiayService.getAll());
        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
        model.addAttribute("lstChatLieu", chatLieuService.getAll());
        model.addAttribute("lstDayGiay", dayGiayService.getAll());
        return "left-menu-san-pham";
    }

    @PostMapping("/san-pham/update/{id}")
    public String update(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<SanPham> page = spService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-san-pham";
        }
        model.addAttribute("sanPham", new SanPham());
        spService.addOrUpdate(sanPham);
        Page<SanPham> page = spService.pagination(p, 10);
        model.addAttribute("lst", page);
        model.addAttribute("lstDeGiay", deGiayService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAll());
        model.addAttribute("lstLotGiay", lotGiayService.getAll());
        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
        model.addAttribute("lstChatLieu", chatLieuService.getAll());
        model.addAttribute("lstDayGiay", dayGiayService.getAll());
        return "redirect:/san-pham";
    }

    @GetMapping("/san-pham/remove/{id}")
    public String remove(@ModelAttribute("sanPham") SanPham sanPham, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        spService.remove(id);
        Page<SanPham> page = spService.pagination(p, 10);
        model.addAttribute("lst", page);
        model.addAttribute("lstDeGiay", deGiayService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAll());
        model.addAttribute("lstLotGiay", lotGiayService.getAll());
        model.addAttribute("lstMuiGiay", muiGiayService.getAll());
        model.addAttribute("lstChatLieu", chatLieuService.getAll());
        model.addAttribute("lstDayGiay", dayGiayService.getAll());
        return "redirect:/san-pham";
    }
}
