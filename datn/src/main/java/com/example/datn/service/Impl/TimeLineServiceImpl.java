package com.example.datn.service.Impl;

import com.example.datn.entity.HinhThucThanhToan;
import com.example.datn.entity.HoaDon;
import com.example.datn.entity.PhuongThucThanhToan;
import com.example.datn.entity.TimeLine;
import com.example.datn.repository.HinhThucThanhToanRepository;
import com.example.datn.repository.HoaDonRepository;
import com.example.datn.repository.PhuongThucThanhToanRepository;
import com.example.datn.repository.TimeLineRepository;
import com.example.datn.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public TimeLine xacNhanHoaDon(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(2);
            hoaDonRepository.save(hoaDon);
        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(2);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public TimeLine choGiaoDonHang(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(3);
            hoaDonRepository.save(hoaDon);
        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(3);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public TimeLine dangGiaoDonHang(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(4);
            hoaDonRepository.save(hoaDon);
        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(4);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public TimeLine daGiaoDonHang(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(5);
            hoaDonRepository.save(hoaDon);
        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(5);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public TimeLine hoanThanhDonHang(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(6);
            hoaDon.setThanhToan(true);
            hoaDonRepository.save(hoaDon);

        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(6);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public TimeLine huyDonHang(Long id, String mota) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(7);
            hoaDonRepository.save(hoaDon);
        }
        TimeLine timeLine = new TimeLine();
        timeLine.setNgayTao(LocalDate.now());
        timeLine.setHoaDon(hoaDon);
        timeLine.setMoTa(mota);
        timeLine.setTrangThai(7);
        return timeLineRepository.save(timeLine);
    }

    @Override
    public void deleteTimeLine(Long id) {
        TimeLine timeLine = timeLineRepository.findById(id).orElse(null);
        if (timeLine != null) {
            HoaDon hoaDon = hoaDonRepository.findById(timeLine.getHoaDon().getHoaDonId()).orElse(null);
            if (hoaDon != null) {
                List<TimeLine> timeLines = timeLineRepository.findByHoaDonId(hoaDon.getHoaDonId());
                if (!timeLines.isEmpty()) {
                    TimeLine lastTimeLine = timeLines.get(timeLines.size() - 2);
                    hoaDon.setTrangThai(lastTimeLine.getTrangThai());
                    hoaDonRepository.save(hoaDon);

                    TimeLine tl = new TimeLine();
                    tl.setNgayTao(LocalDate.now());
                    tl.setHoaDon(hoaDon);
                    tl.setTrangThai(lastTimeLine.getTrangThai());
                    timeLineRepository.save(tl);
                }
            }
        }
    }

    @Override
    public TimeLine getTimeLineById(Long id) {
        return timeLineRepository.findById(id).orElse(null);
    }

}
