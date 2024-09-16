package com.example.datn.service;

import com.example.datn.entity.TimeLine;
import jakarta.mail.MessagingException;

public interface TimeLineService {

    TimeLine xacNhanHoaDon(Long id, String mota) throws MessagingException;

    TimeLine choGiaoDonHang(Long id, String mota) throws MessagingException;

    TimeLine dangGiaoDonHang(Long id, String mota) throws MessagingException;

    TimeLine daGiaoDonHang(Long id, String mota) throws MessagingException;

    TimeLine hoanThanhDonHang(Long id, String mota) throws MessagingException;

    TimeLine huyDonHang(Long id, String mota) throws MessagingException;

    void deleteTimeLine(Long id) throws MessagingException;

    TimeLine getTimeLineById(Long id);

}
