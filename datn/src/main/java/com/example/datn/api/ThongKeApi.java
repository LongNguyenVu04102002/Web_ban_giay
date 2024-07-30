//package com.example.datn.api;
//
//import com.example.datn.service.ThongKeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/api/thong-ke")
//public class ThongKeApi {
//
//    @Autowired
//    private ThongKeService thongKeService;
//
//    @GetMapping("/today")
//    public List<String> getHoaDonToday(){
//        return thongKeService.getHoaDonToday();
//    }
//
//    //dem khach hang hom nay
//    @GetMapping("/countKhachHangToday")
//    public Long countKhachHangToday(@RequestParam("trangThai") int trangThai){
//        LocalDate today = LocalDate.now();
//        return thongKeService.countHoaDonByNgayTaoAndTrangThai(today, trangThai);
//    }
//
//    // dem khanh hang hom qua
//    @GetMapping("/countKhachHangYesterday")
//    public Long countKhachHangYesterday(@RequestParam("trangThai") int trangThai){
//        LocalDate yesterday = LocalDate.now().minusDays(1);
//        return thongKeService.countKhachHangByNgayTaoAndTrangThai(yesterday, trangThai);
//    }
//
//    @GetMapping("/countHoaDonToday")
//    public Long countHoaDonToday(@RequestParam("trangThai") int trangThai){
//        LocalDate today = LocalDate.now();
//        return thongKeService.countHoaDonByNgayTaoAndTrangThai(today, trangThai);
//    }
//
//    @GetMapping("/count")
//}
