package com.example.datn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GioHangChiTietDTO {
    private Long gioHangChiTietId;
    private Long sanPhamChiTietId;
    private Integer soLuong;
    private LocalDate ngayTao;

}
