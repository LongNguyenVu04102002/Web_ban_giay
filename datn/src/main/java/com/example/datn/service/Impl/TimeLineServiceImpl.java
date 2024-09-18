package com.example.datn.service.Impl;

import com.example.datn.dto.MyUserDetail;
import com.example.datn.entity.HinhThucThanhToan;
import com.example.datn.entity.HoaDon;
import com.example.datn.entity.HoaDonChiTiet;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.PhuongThucThanhToan;
import com.example.datn.entity.TimeLine;
import com.example.datn.repository.HinhThucThanhToanRepository;
import com.example.datn.repository.HoaDonRepository;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.repository.PhuongThucThanhToanRepository;
import com.example.datn.repository.SanPhamChiTietRepository;
import com.example.datn.repository.TimeLineRepository;
import com.example.datn.service.TimeLineService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeLineServiceImpl implements TimeLineService {
    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public TimeLine xacNhanHoaDon(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(2);
        hoaDonRepository.save(hoaDon);

        hoaDon.getHoaDonChiTietList().forEach(ghct -> {
            sanPhamChiTietRepository.findById(ghct.getSanPhamChiTiet().getSanPhamChiTietId()).ifPresent(spct -> {
                spct.setSoLuong(spct.getSoLuong() - ghct.getSoLuong());
                sanPhamChiTietRepository.save(spct);
            });
        });

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 2, "Đã xác nhận");
        return getTimeLine(timeLine);
    }

    @Override
    public TimeLine choGiaoDonHang(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(3);
        hoaDonRepository.save(hoaDon);

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 3, "Chờ giao hàng");
        return getTimeLine(timeLine);
    }

    @Override
    public TimeLine dangGiaoDonHang(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(4);
        hoaDonRepository.save(hoaDon);

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 4, "Đang giao hàng");
        return getTimeLine(timeLine);
    }

    @Override
    public TimeLine daGiaoDonHang(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(5);
        hoaDonRepository.save(hoaDon);

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 5, "Đã giao");
        return getTimeLine(timeLine);
    }

    @Override
    public TimeLine hoanThanhDonHang(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(6);
        hoaDon.setThanhToan(true);
        hoaDonRepository.save(hoaDon);

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 6, "Hoàn thành");
        return getTimeLine(timeLine);
    }

    @Override
    public TimeLine huyDonHang(Long id, String mota) throws MessagingException {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon == null) {
            return null;
        }

        hoaDon.setTrangThai(7);
        hoaDonRepository.save(hoaDon);

        TimeLine timeLine = createAndSendEmail(hoaDon, mota, 7, "Đã hủy");
        return getTimeLine(timeLine);
    }


    @Override
    public void deleteTimeLine(Long id) throws MessagingException {
        TimeLine timeLine = timeLineRepository.findById(id).orElse(null);
        if (timeLine != null) {
            HoaDon hoaDon = hoaDonRepository.findById(timeLine.getHoaDon().getHoaDonId()).orElse(null);
            if (hoaDon != null) {
                List<TimeLine> timeLines = timeLineRepository.findByHoaDonId(hoaDon.getHoaDonId());
                if (timeLines.size() >= 2) {
                    TimeLine lastTimeLine = timeLines.get(timeLines.size() - 2); // Lấy phần tử gần cuối
                    hoaDon.setTrangThai(lastTimeLine.getTrangThai());
                    hoaDonRepository.save(hoaDon);

                    TimeLine tl = new TimeLine();
                    tl.setNgayTao(LocalDate.now());
                    tl.setHoaDon(hoaDon);
                    tl.setTrangThai(lastTimeLine.getTrangThai());
                    getTimeLine(tl);

                    String email = hoaDon.getEmail();
                    if (email != null && !email.isEmpty()) {
                        String status = getStatus(lastTimeLine.getTrangThai());
                        emailService.sendEmail(email, "Cập Nhật Trạng Thái Hóa Đơn",
                                "Trạng thái hóa đơn " + hoaDon.getMaVanDon() + " đã chuyển thành " + status);
                        System.out.println(status);
                    }
                }

                for (HoaDonChiTiet ghct : hoaDon.getHoaDonChiTietList()) {
                    sanPhamChiTietRepository.findById(ghct.getSanPhamChiTiet().getSanPhamChiTietId()).ifPresent(spct -> {
                        spct.setSoLuong(spct.getSoLuong() + ghct.getSoLuong());
                        sanPhamChiTietRepository.save(spct);
                    });
                }
            }
        }
    }

    private String getStatus(int trangThai) {
        return switch (trangThai) {
            case 2 -> "Đã xác nhận";
            case 3 -> "Chờ giao hàng";
            case 4 -> "Đang giao hàng";
            case 5 -> "Đã giao";
            case 6 -> "Hoàn thành";
            case 7 -> "Đã hủy";
            default -> "Không rõ";
        };
    }


    private TimeLine createAndSendEmail(HoaDon hoaDon, String mota, int trangThai, String statusMessage) throws MessagingException {
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(trangThai);

        emailService.sendEmail(
                hoaDon.getEmail(),
                "Cập Nhật Trạng Thái Hóa Đơn",
                "Trạng thái hóa đơn " + hoaDon.getMaVanDon() + " đã chuyển thành " + statusMessage
        );

        return timeLine;
    }

    @Override
    public TimeLine getTimeLineById(Long id) {
        return timeLineRepository.findById(id).orElse(null);
    }

    private TimeLine getTimeLine(TimeLine timeLine) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof MyUserDetail) {
                Long nhanVienId = ((MyUserDetail) principal).getId();
                Optional<NhanVien> nhanVien = nhanVienRepository.findById(nhanVienId);
                nhanVien.ifPresent(vien -> timeLine.setNguoiThucHien(vien.getMaNhanVien()));
            }
        }
        return timeLineRepository.save(timeLine);
    }

}
