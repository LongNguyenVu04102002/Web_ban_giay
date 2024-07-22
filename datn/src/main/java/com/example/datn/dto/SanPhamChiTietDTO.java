package com.example.datn.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTietDTO {

    private Long sanPhamChiTietId;
    private String barCode;
    private BigDecimal giaBan;
    private Integer soLuong;
    private boolean trangThai;
    private Long kichThuocId;
    private Long mauSacId;
    private Long sanPhamId;
    private List<HinhAnhDTO> lstAnh; // Danh sách file ảnh
}

