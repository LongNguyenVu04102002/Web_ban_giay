package com.example.datn.controller;

import com.example.datn.dto.TabDataDTO;
import com.example.datn.entity.GioHang;
import com.example.datn.service.Impl.ChatLieuServiceImpl;
import com.example.datn.service.Impl.CoGiayServiceImpl;
import com.example.datn.service.Impl.DayGiayServiceImpl;
import com.example.datn.service.Impl.DeGiayServiceImpl;
import com.example.datn.service.Impl.GioHangServiceImpl;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import com.example.datn.service.Impl.KichThuocServiceImpl;
import com.example.datn.service.Impl.LotGiayServiceImpl;
import com.example.datn.service.Impl.MauSacServiceImpl;
import com.example.datn.service.Impl.MuiGiayServiceImpl;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import com.example.datn.service.Impl.SanPhamServiceImpl;
import com.example.datn.service.Impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private MauSacServiceImpl mauSacService;

    @Autowired
    private KichThuocServiceImpl kichThuocService;

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

    @GetMapping("/banhang")
    private String view(Model model) {
        return GioHangList(model);
    }

    private String GioHangList(Model model) {
        List<GioHang> gioHangList = new ArrayList<>();
        List<TabDataDTO> tabDataList = new ArrayList<>();

        for (long i = 1L; i <= 5L; i++) {
            GioHang gioHang = gioHangService.getById(i);
            if (gioHang == null) {
                gioHang = new GioHang();
                gioHang.setGioHangId(i);
                gioHangService.save(gioHang);
            }
            gioHangList.add(gioHang);
            TabDataDTO tabDataDTO = gioHangService.updateCart(gioHang);
            tabDataList.add(tabDataDTO);
        }

        BigDecimal tongTienGiam = (BigDecimal) model.asMap().getOrDefault("tongTienGiam", BigDecimal.ZERO);
        model.addAttribute("tabDataList", tabDataList);
        model.addAttribute("gioHangList", gioHangList);
        model.addAttribute("tongTienGiam", tongTienGiam);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietService.getAll());
        model.addAttribute("khachHangList", khachHangService.getAll());
        model.addAttribute("lstDeGiay", deGiayService.getAllDeGiay());
        model.addAttribute("lstMauSac", mauSacService.getAll());
        model.addAttribute("lstThuongHieu", thuongHieuService.getAll());
        model.addAttribute("lstKichThuoc", kichThuocService.getAll());
        model.addAttribute("lstCoGiay", coGiayService.getAllCoGiay());
        model.addAttribute("lstLotGiay", lotGiayService.getAllLotGiay());
        model.addAttribute("lstMuiGiay", muiGiayService.getAllMuiGiay());
        model.addAttribute("lstChatLieu", chatLieuService.getAllChatLieu());
        model.addAttribute("lstDayGiay", dayGiayService.getAllDayGiay());

        return "/admin/includes/content/banhang/home";
    }

    @PostMapping("/banhang/addToCart")
    private String addToCart(@RequestParam Long gioHangId,
                             @RequestParam Long sanPhamChiTietId,
                             Model model) {
        model.addAttribute("success", true);
        gioHangService.addToCart(gioHangId, sanPhamChiTietId);
        return GioHangList(model);
    }

    @GetMapping("/banhang/update/stepdown")
    public String stepDown(@RequestParam("gioHangChiTietId") Long gioHangChiTietId,
                           @RequestParam("sanPhamChiTietId") Long sanPhamChiTietId,
                           Model model) {
        model.addAttribute("stepdown", true);
        gioHangService.stepDown(gioHangChiTietId, sanPhamChiTietId);
        return GioHangList(model);
    }

    @GetMapping("/banhang/update/stepup")
    public String stepUp(@RequestParam("gioHangChiTietId") Long gioHangChiTietId,
                         @RequestParam("sanPhamChiTietId") Long sanPhamChiTietId,
                         Model model) {
        model.addAttribute("stepup", true);
        gioHangService.stepUp(gioHangChiTietId, sanPhamChiTietId);
        return GioHangList(model);
    }

    @GetMapping("/banhang/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deletes", true);
        gioHangService.delete(id);
        return GioHangList(model);
    }

    @PostMapping("/banhang/save")
    public String save(@RequestParam Long gioHangId,
                       @RequestParam(defaultValue = "0") Long customerId,
                       @RequestParam(defaultValue = "") String discountCode,
                       @RequestParam(defaultValue = "0") BigDecimal discountAmount,
                       @RequestParam(defaultValue = "0") BigDecimal totalAmount,
                       @RequestParam Long thanhToan) {
        hoaDonService.saveHoaDonTaiQuay(gioHangId, customerId, discountCode, discountAmount, totalAmount, thanhToan);
        return "redirect:/admin/hoadon";
    }

}
