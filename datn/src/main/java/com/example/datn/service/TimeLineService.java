package com.example.datn.service;

import com.example.datn.entity.TimeLine;

public interface TimeLineService {

    TimeLine xacNhanHoaDon(Long id, String mota);

    TimeLine choGiaoDonHang(Long id, String mota);

    TimeLine dangGiaoDonHang(Long id, String mota);

    TimeLine daGiaoDonHang(Long id, String mota);

    TimeLine hoanThanhDonHang(Long id, String mota);

    TimeLine huyDonHang(Long id, String mota);

    void deleteTimeLine(Long id);

    TimeLine getTimeLineById(Long id);

}
