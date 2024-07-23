package com.example.datn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class HinhAnhDTO {

    private Long hinhAnhId;
    private String link;
    private Integer uuTien;
    private Long sanPhamChiTietId;
}
