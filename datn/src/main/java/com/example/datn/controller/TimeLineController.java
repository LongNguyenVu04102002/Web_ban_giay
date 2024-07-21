package com.example.datn.controller;

import com.example.datn.service.impl.TimeLineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/timeline")
public class TimeLineController {
    @Autowired
    private TimeLineServiceImpl timeLineService;

    @PostMapping("/xacnhan/{id}")
    public String xacNhanHoaDon(@PathVariable Long id){
         timeLineService.xacNhanHoaDon(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/chogiao/{id}")
    public String choGiaoDonHang(@PathVariable Long id){
        timeLineService.choGiaoDonHang(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }
    @PostMapping("/danggiao/{id}")
    public String dangGiaoDonHang(@PathVariable Long id){
        timeLineService.dangGiaoDonHang(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }
    @PostMapping("/dagiao/{id}")
    public String daGiaoDonHang(@PathVariable Long id){
        timeLineService.daGiaoDonHang(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }
    @PostMapping("/hoanthanh/{id}")
    public String hoanThanhDonHang(@PathVariable Long id){
        timeLineService.hoanThanhDonHang(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }
    @PostMapping("/huydon/{id}")
    public String huyDonHang(@PathVariable Long id){
        timeLineService.huyDonHang(id);
        return "redirect:/admin/hoadon/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteTimeLing(@PathVariable Long id){
        Long hoaDonId = timeLineService.getTimeLineById(id).getHoaDon().getHoaDonId();
        timeLineService.deleteTimeLine(id);
        return "redirect:/admin/hoadon/detail/" + hoaDonId;
    }


}
