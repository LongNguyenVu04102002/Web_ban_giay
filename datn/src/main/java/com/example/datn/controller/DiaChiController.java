package com.example.datn.controller;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/taikhoan")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private DiaChiRepository diaChiRepository;

    @GetMapping("diaChi/delete/{id}")
    public String deleteDiaChi(@ModelAttribute("khachHang") KhachHang khachHang,
                               @PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            // Gọi phương thức xóa địa chỉ, có thể ném ra ngoại lệ IllegalStateException nếu không xóa được
            diaChiService.deleteDiaChi(id);

            // Nếu xóa thành công, thêm thông báo thành công vào RedirectAttributes
            redirectAttributes.addFlashAttribute("message3", "Xóa địa chỉ thành công.");
        } catch (IllegalStateException e) {
            // Trường hợp khách hàng chỉ có một địa chỉ, ném ngoại lệ này
            redirectAttributes.addFlashAttribute("error1", "Không thể xóa địa chỉ này vì khách hàng phải có ít nhất một địa chỉ.");
        } catch (NoSuchElementException e) {
            // Trường hợp không tìm thấy địa chỉ, ném ngoại lệ này
            redirectAttributes.addFlashAttribute("error1", "Không tìm thấy địa chỉ cần xóa.");
        } catch (Exception e) {
            // Bắt các ngoại lệ khác và hiển thị thông báo lỗi
            redirectAttributes.addFlashAttribute("error1", "Đã xảy ra lỗi khi xóa địa chỉ: " + e.getMessage());
        }

        // Điều hướng về trang chi tiết khách hàng sau khi thực hiện xóa địa chỉ
        return "redirect:/admin/taikhoan/khachhang/detail/" + khachHang.getKhachHangId();
    }


    @PostMapping("/khachhang/updateTrangThai")
    public String updateTrangThai(@RequestParam("diaChiId") Long diaChiId,
                                  @RequestParam("trangThai") boolean trangThai,
                                  RedirectAttributes redirectAttributes) {

      DiaChi diaChi = diaChiService.toggleTrangThai(diaChiId);
      boolean isActive = diaChi.isTrangThai();
      String newStatus = isActive ? "ngừng hoạt động" : "hoat động";

        return "redirect:/admin/taikhoan/khachhang/detail/" + diaChi.getKhachHang().getKhachHangId();
    }

    @GetMapping("/diaChi/edit")
    public String showUpdateForm(@RequestParam("diaChiId") Long diaChiId, Model model) {
        DiaChi diaChi = diaChiService.findById(diaChiId);
        if (diaChi == null) {
            // Xử lý nếu địa chỉ không tồn tại (tùy thuộc vào yêu cầu của bạn)
            return "redirect:/error"; // Hoặc một trang khác
        }
        model.addAttribute("diaChi1", diaChi);
        return "admin/includes/content/khachhang/editDiaChi";
    }


    @PostMapping("/diaChi/update")
    public String updateDiaChi(@ModelAttribute DiaChi diaChi1, BindingResult result) {
        System.out.println("Received diaChi: " + diaChi1);
        if (result.hasErrors()) {
            return "admin/includes/content/khachhang/editDiaChi";
        }
        diaChiService.save(diaChi1);
        return "redirect:/admin/taikhoan/khachhang/detail/" + diaChi1.getKhachHang().getKhachHangId();
    }





}
