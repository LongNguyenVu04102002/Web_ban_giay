package com.example.datn.api;

import com.example.datn.entity.HoaDon;
import com.example.datn.model.response.HoaDonResponse;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoadon")
public class HoaDonApi {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @GetMapping("/detail/{id}")
    public String getHoaDonById(@PathVariable Long id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/includes/content/hoadon/hoadonchitiet";

    }

    @PostMapping("/save")
    public ResponseEntity<?> saveHoaDon(@RequestBody HoaDonResponse hoaDonResponse) {
        return ResponseEntity.ok(hoaDonService.addHoaDon(hoaDonResponse));

    }

}
