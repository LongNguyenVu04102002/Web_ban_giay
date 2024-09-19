package com.example.datn.service;

import com.example.datn.entity.TimeLine;
import jakarta.mail.MessagingException;

public interface TimeLineService {

    boolean xacNhanHoaDon(Long id, String mota) throws MessagingException;

    boolean choGiaoDonHang(Long id, String mota) throws MessagingException;

    boolean dangGiaoDonHang(Long id, String mota) throws MessagingException;

    boolean daGiaoDonHang(Long id, String mota) throws MessagingException;

    boolean hoanThanhDonHang(Long id, String mota) throws MessagingException;

    boolean huyDonHang(Long id, String mota) throws MessagingException;

    void deleteTimeLine(Long id) throws MessagingException;

    TimeLine getTimeLineById(Long id);

}
