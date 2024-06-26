package com.example.datn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "phieuGiamGia")
public class PhieuGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phieuGiamGiaId", nullable = false)
    private Long phieuGiamGiaId;

    @Size(max = 10)
    @NotNull
    @Column(name = "maGiamGia", nullable = false, length = 10)
    private String maGiamGia;

    @Column(name = "phanTramGiam")
    private Integer phanTramGiam;

    @Column(name = "tienGiam")
    private BigDecimal tienGiam;

    @Column(name = "loaiPhieu", nullable = false)
    private Integer loaiPhieu;

    @Column(name = "soLuongPhieu")
    private Integer soLuongPhieu;

    @Column(name = "ngayBatDau")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThuc;

    @Column(name = "giaTriDonToiThieu", precision = 18)
    private BigDecimal giaTriDonToiThieu;

    @Column(name = "giaTriGiamToiDa", precision = 18)
    private BigDecimal giaTriGiamToiDa;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "trangThai")
    private String trangThai;

    public String getTrangThaiHienTai() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(ngayBatDau)) {
            return "Sắp diễn ra";
        } else if (!currentDate.isBefore(ngayBatDau) && !currentDate.isAfter(ngayKetThuc)) {
            return "Đang diễn ra";
        } else {
            return "Kết thúc";
        }
    }
}