package com.example.datn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GioHangDTO {

    private Long gioHangId;
    private Long khachHangId;
    private LocalDate ngayTao;
    private boolean trangThai;
    private List<GioHangChiTietDTO> gioHangChiTietList;
}
