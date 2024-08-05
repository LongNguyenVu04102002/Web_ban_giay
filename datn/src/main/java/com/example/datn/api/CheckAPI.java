package com.example.datn.api;

import com.example.datn.service.ChatLieuService;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckAPI {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/add-spct")
    public boolean checkAddSPCT(@RequestParam Long sanPhamId, @RequestParam Long kichThuocId, @RequestParam Long mauSacId) {
        return sanPhamChiTietService.findBySanPham_SanPhamIdAndKichThuoc_KichThuocIdAndMauSac_MauSacId(sanPhamId, kichThuocId, mauSacId);
    }

    @GetMapping("/add-cl")
    public boolean checkAddChatLieu(@RequestParam String ten) {
        ten = ten.trim();
        return chatLieuService.isTenExists(ten);
    }

    @GetMapping("/update-cl")
    public boolean checkUpdateChatLieu(@RequestParam Long id, @RequestParam String ten) {
        return chatLieuService.isTenExistsForUpdate(ten.trim(), id);
    }
}
