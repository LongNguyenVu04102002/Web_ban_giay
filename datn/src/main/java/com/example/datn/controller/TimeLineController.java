package com.example.datn.controller;

import com.example.datn.service.Impl.TimeLineServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/timeline")
public class TimeLineController {
    @Autowired
    private TimeLineServiceImpl timeLineService;

    @PostMapping("/xacnhan/{id}")
    public String xacNhanHoaDon(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.xacNhanHoaDon(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/chogiao/{id}")
    public String choGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.choGiaoDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/danggiao/{id}")
    public String dangGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.dangGiaoDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/dagiao/{id}")
    public String daGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.daGiaoDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/hoanthanh/{id}")
    public String hoanThanhDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.hoanThanhDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/huydon/{id}")
    public String huyDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        timeLineService.huyDonHang(id, mota);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteTimeLing(@PathVariable Long id,RedirectAttributes redirectAttributes) throws MessagingException {
        Long hoaDonId = timeLineService.getTimeLineById(id).getHoaDon().getHoaDonId();
        timeLineService.deleteTimeLine(id);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/hoadon/detail/" + hoaDonId;
    }

}
