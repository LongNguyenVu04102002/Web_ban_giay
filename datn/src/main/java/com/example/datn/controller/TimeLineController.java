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
        boolean xacNhan = timeLineService.xacNhanHoaDon(id, mota);
        if(xacNhan){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/chogiao/{id}")
    public String choGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        boolean choGiao = timeLineService.choGiaoDonHang(id, mota);
        if(choGiao){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/danggiao/{id}")
    public String dangGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        boolean dangGiao = timeLineService.dangGiaoDonHang(id, mota);
        if(dangGiao){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/dagiao/{id}")
    public String daGiaoDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        boolean daGiao = timeLineService.daGiaoDonHang(id, mota);
        if(daGiao){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/hoanthanh/{id}")
    public String hoanThanhDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        boolean hoanThanh = timeLineService.hoanThanhDonHang(id, mota);
        if(hoanThanh){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/huydon/{id}")
    public String huyDonHang(@PathVariable Long id, @RequestParam String mota, RedirectAttributes redirectAttributes) throws MessagingException {
        boolean huyDon = timeLineService.huyDonHang(id, mota);
        if(huyDon){
            redirectAttributes.addFlashAttribute("success", true);
        }else {
            redirectAttributes.addFlashAttribute("error", true);
        }
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
