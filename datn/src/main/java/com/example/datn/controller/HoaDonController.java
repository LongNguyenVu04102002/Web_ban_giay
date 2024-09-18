package com.example.datn.controller;

import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.service.HinhAnhService;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import com.example.datn.service.Impl.NhanVienServiceImpl;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Base64;
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

    @Autowired
    private HinhAnhService hinhAnhService;

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
        model.addAttribute("hoaDonDangGiaoHang", hoaDonDangGiaoHang);
        model.addAttribute("hoaDonDaGiaoHang", hoaDonDaGiaoHang);
        model.addAttribute("hoaDonHoanThanh", hoaDonHoanThanh);
        model.addAttribute("hoaDonHuy", hoaDonHuy);
        model.addAttribute("nhanVien", nhanViens);
        return "admin/includes/content/hoadon/hoadon";
    }

    @GetMapping("/hoadon/detail/{id}")
    public String getHoaDonById(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        getImage(model, hoaDon);
        return "admin/includes/content/hoadon/hoadonchitiet";
    }

    private void getImage(Model model, HoaDon hoaDon) {
        for (HoaDonChiTiet hdct : hoaDon.getHoaDonChiTietList()) {
            SanPhamChiTiet spct = hdct.getSanPhamChiTiet();
            byte[] imageBytes = hinhAnhService.getImageBySanPhamChiTietIdWithPriority(spct.getSanPhamChiTietId(), 1);
            if (imageBytes != null) {
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                spct.setBase64Image(base64Image);
            }
        }
        model.addAttribute("hoaDon", hoaDon);
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
        List<SanPhamChiTiet> sanPhamChiTietList = sanPhamChiTietService.findAllByTrangThaiTrue();
        getImage(model, hoaDon);
        model.addAttribute("sanPhamChiTietList", sanPhamChiTietList);
        return "admin/includes/content/hoadon/cartdetail";
    }

    @PostMapping("/hoadon/update")
    public String update(@RequestParam Long idHoaDon, @RequestParam Long idSanPhamChiTiet, RedirectAttributes redirectAttributes) {
        boolean add = hoaDonService.update(idHoaDon, idSanPhamChiTiet);
        if (add) {
            redirectAttributes.addFlashAttribute("add", true);
        } else {
            redirectAttributes.addFlashAttribute("info", true);
        }
        return "redirect:/admin/hoadon/cartdetail/" + idHoaDon;
    }

    @PostMapping("/hoadon/update/stepdown")
    public String stepDown(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.stepDown(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("stepdown", true);
        return "redirect:/admin/hoadon/cartdetail/" + hoaDonId;
    }

    @PostMapping("/hoadon/update/stepup")
    public String stepUp(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.stepUp(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("stepup", true);
        return "redirect:/admin/hoadon/cartdetail/" + hoaDonId;
    }

    @PostMapping("/hoadon/update/thongtingiaohang")
    public String updateThongTinGiaoHang(@ModelAttribute HoaDon hoaDon, RedirectAttributes redirectAttributes) {
        hoaDonService.updateThongTinGiaoHang(hoaDon);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/admin/hoadon/detail/" + hoaDon.getHoaDonId();
    }

    @PostMapping("/hoadon/delete")
    public String delete(@RequestParam Long hoaDonId, @RequestParam Long hoaDonChiTietId, RedirectAttributes redirectAttributes) {
        hoaDonService.delete(hoaDonId, hoaDonChiTietId);
        redirectAttributes.addFlashAttribute("deletes", true);
        return "redirect:/admin/hoadon/cartdetail/" + hoaDonId;
    }

}
