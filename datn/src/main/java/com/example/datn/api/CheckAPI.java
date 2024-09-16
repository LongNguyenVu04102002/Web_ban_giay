package com.example.datn.api;

import com.example.datn.service.ChatLieuService;
import com.example.datn.service.CoGiayService;
import com.example.datn.service.DayGiayService;
import com.example.datn.service.DeGiayService;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import com.example.datn.service.KichThuocService;
import com.example.datn.service.LotGiayService;
import com.example.datn.service.MauSacService;
import com.example.datn.service.MuiGiayService;
import com.example.datn.service.SanPhamService;
import com.example.datn.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private CoGiayService coGiayService;

    @Autowired
    private DayGiayService dayGiayService;

    @Autowired
    private DeGiayService deGiayService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private LotGiayService lotGiayService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private MuiGiayService muiGiayService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/add-spct")
    public boolean checkAddSPCT(@RequestParam Long sanPhamId, @RequestParam Long kichThuocId, @RequestParam Long mauSacId) {
        return sanPhamChiTietService.findBySanPham_SanPhamIdAndKichThuoc_KichThuocIdAndMauSac_MauSacId(sanPhamId, kichThuocId, mauSacId);
    }

    @GetMapping("/update-spct")
    public ResponseEntity<Boolean> checkDuplicate(
            @RequestParam Long sanPhamId,
            @RequestParam Long kichThuocId,
            @RequestParam Long mauSacId,
            @RequestParam(required = false) Long sanPhamChiTietId) {
        boolean isDuplicate = sanPhamChiTietService.isDuplicate(sanPhamId, kichThuocId, mauSacId, sanPhamChiTietId);
        return ResponseEntity.ok(isDuplicate);
    }

    @GetMapping("/addAndUpdate-sp")
    public boolean checkAddAndUpdateSp(
            @RequestParam String ten,
            @RequestParam Long chatLieuId,
            @RequestParam Long coGiayId,
            @RequestParam Long dayGiayId,
            @RequestParam Long deGiayId,
            @RequestParam Long lotGiayId,
            @RequestParam Long muiGiayId,
            @RequestParam Long thuongHieuId,
            @RequestParam(required = false) Long sanPhamId) {
        return sanPhamService.findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(ten.trim(), chatLieuId, coGiayId, dayGiayId, deGiayId, lotGiayId, muiGiayId, thuongHieuId, sanPhamId);
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

    @GetMapping("/add-cg")
    public boolean checkAddCoGiay(@RequestParam String ten) {
        ten = ten.trim();
        return coGiayService.isTenExists(ten);
    }

    @GetMapping("update-cg")
    public boolean checkUpdateCoGiay(@RequestParam Long id, @RequestParam String ten) {
        return coGiayService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-dg")
    public boolean checkAddDayGiay(@RequestParam String ten) {
        ten = ten.trim();
        return dayGiayService.isTenExists(ten);
    }

    @GetMapping("/update-dg")
    public boolean checkUpdateDayGiay(@RequestParam Long id, @RequestParam String ten) {
        return dayGiayService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-de-giay")
    public boolean checkAddDeGiay(@RequestParam String ten) {
        ten = ten.trim();
        return deGiayService.isTenExists(ten);
    }

    @GetMapping("/update-de-giay")
    public boolean checkUpdateDeGiay(@RequestParam Long id, @RequestParam String ten) {
        return deGiayService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-kt")
    public boolean checkAddKichThuoc(@RequestParam String ten) {
        ten = ten.trim();
        return kichThuocService.isTenExists(ten);
    }

    @GetMapping("/update-kt")
    public boolean checkUpdateKichThuoc(@RequestParam Long id, @RequestParam String ten) {
        return kichThuocService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-lg")
    public boolean checkAddLotGiay(@RequestParam String ten) {
        ten = ten.trim();
        return lotGiayService.isTenExists(ten);
    }

    @GetMapping("/update-lg")
    public boolean checkUpdateLotGiay(@RequestParam Long id, @RequestParam String ten) {
        return lotGiayService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-ms")
    public boolean checkAddMauSac(@RequestParam String ten) {
        ten = ten.trim();
        return mauSacService.isTenExists(ten);
    }

    @GetMapping("/update-ms")
    public boolean checkUpdateMauSac(@RequestParam Long id, @RequestParam String ten) {
        return mauSacService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-mg")
    public boolean checkAddMuiGiay(@RequestParam String ten) {
        ten = ten.trim();
        return muiGiayService.isTenExists(ten);
    }

    @GetMapping("/update-mg")
    public boolean checkUpdateMuiGiay(@RequestParam Long id, @RequestParam String ten) {
        return muiGiayService.isTenExistsForUpdate(ten.trim(), id);
    }

    @GetMapping("/add-th")
    public boolean checkAddThuongHieu(@RequestParam String ten) {
        ten = ten.trim();
        return thuongHieuService.isTenExists(ten);
    }

    @GetMapping("/update-th")
    public boolean checkUpdateThuongHieu(@RequestParam Long id, @RequestParam String ten) {
        return thuongHieuService.isTenExistsForUpdate(ten.trim(), id);
    }

}
