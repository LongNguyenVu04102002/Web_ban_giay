package com.example.datn.service;

import com.example.datn.entity.TimeLine;

public interface TimeLineService {

    TimeLine xacNhanHoaDon(Long id);

    TimeLine choGiaoDonHang(Long id);

    TimeLine dangGiaoDonHang(Long id);

    TimeLine daGiaoDonHang(Long id);

    TimeLine hoanThanhDonHang(Long id);

    TimeLine huyDonHang(Long id);

    void deleteTimeLine(Long id);

    TimeLine getTimeLineById(Long id);

}
