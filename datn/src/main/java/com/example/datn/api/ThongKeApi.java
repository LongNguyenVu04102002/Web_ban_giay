package com.example.datn.api;

import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;
import com.example.datn.entity.ThuongHieu;
import com.example.datn.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/api/thong-ke")
public class ThongKeApi {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/today")
    public List<String> getHoaDonToday() {
        return thongKeService.getHoaDonToday();
    }

    //dem hoa don hom nay
    @GetMapping("/countHoaDonToday")
    public Long countHoaDonToday(@RequestParam("trangThai") int trangThai) {
        LocalDate today = LocalDate.now();
        return thongKeService.countHoaDonByNgayTaoAndTrangThai(today, trangThai);
    }

    //dem hoa don tuan nay
    @GetMapping("/countHoaDonForCurrentWeek")
    public List<Long> countHoaDonForCurrentWeek(@RequestParam("trangThai") int trangThai) {
        return thongKeService.countHoaDonForCurrentWeek(trangThai);
    }

    //so luong ton kho
    @GetMapping("/listThuongHieu")
    public List<ThuongHieu> listThuongHieu() {
        return thongKeService.listThuongHieu();
    }

    @GetMapping("/listSanPhamByThuongHieu")
    public List<SanPham> listSanPhamByThuongHieu(@RequestParam("id") Long id) {
        return thongKeService.listSanPhamByThuongHieu(id);
    }

    @GetMapping("/listMauSacBySanPham")
    public List<MauSac> listMauSacBySanPham(@RequestParam("id") Long id) {
        return thongKeService.listMauSacBySanPham(id);
    }

    @GetMapping("/nameSpOfSanPhamChiTiet")
    public List<String> nameSpOfSanPhamChiTiet(@RequestParam("idSp") Long idSp, @RequestParam("idMs") Long idMs) {
        return thongKeService.nameSpOfSanPhamChiTiet(idSp, idMs);
    }

    @GetMapping("/countSoLuongTonOfSanPhamChiTiet")
    public List<Integer> countSoLuongTonOfSanPhamChiTiet(@RequestParam("idSp") Long isSp, @RequestParam("idMs") Long idMs) {
        return thongKeService.countSoLuongTonOfSanPhamChiTiet(isSp, idMs);
    }

    //so luong ban san pham
    @GetMapping("/countSoLuongBanOfSanPhamChiTiet")
    public List<Integer> countSoLuongBanOfSanPhamChiTiet(@RequestParam("idSp") Long idSp, @RequestParam("idMs") Long idMs) {
        return thongKeService.countSoLuongBanOfSanPhamChiTiet(idSp, idMs);
    }

    //gia ban san pham
    @GetMapping("/giaBanOfSanPhamChiTiet")
    public List<BigDecimal> giaBanOfSanPhamChiTiet(@RequestParam("idSp") Long idSp, @RequestParam("idMs") Long idMs) {
        return thongKeService.giaBanOfSanPhamChiTiet(idSp, idMs);
    }

    //don hang theo thang
    @GetMapping("/countsDonHangByMonth")
    public List<Long> countsDonHangByMonth(@RequestParam("start") Integer start, @RequestParam("end") Integer end, @RequestParam("trangThai") int trangThai) {
        return thongKeService.countsDonHangByMonth(start, end, trangThai);
    }

    //don hang tong theo thang
    @GetMapping("/sumDonHangByMonth")
    public List<Integer> sumDonHangByMonth(@RequestParam("start") Integer start, @RequestParam("end") Integer end, @RequestParam("trangThai") int trangThai) {
        return thongKeService.sumDonHangByMonth(start, end, trangThai);
    }

    @GetMapping("/sumDonHangByDay")
    public Double sumDonHangByDay(@RequestParam("trangThai") int trangThai){
        return thongKeService.sumDonHangByDay(trangThai);
    }

    @GetMapping("/getNumbersInRange")
    public List<Integer> getNumbersInRange(@RequestParam("start") Integer start, @RequestParam("end") Integer end) {
        return thongKeService.getNumbersInRange(start, end);
    }
}
