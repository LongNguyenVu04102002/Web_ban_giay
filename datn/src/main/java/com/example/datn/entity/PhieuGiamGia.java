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

    @Column(name = "phanTramGiam", nullable = false)
    private Integer phanTramGiam;

    @Column(name = "tienGiam", nullable = false)
    private Integer tienGiam;

    @Column(name = "loaiPhieu", nullable = false)
    private Integer loaiPhieu;

    @Column(name = "soLuongPhieu")
    private Integer soLuongPhieu;

    @Column(name = "ngayBatDau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc")
    private LocalDate ngayKetThuc;

    @Column(name = "giaTriDonToiThieu", precision = 18)
    private BigDecimal giaTriDonToiThieu;

    @Column(name = "giaTriGiamToiDa", precision = 18)
    private BigDecimal giaTriGiamToiDa;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "trangThai")
    private boolean trangThai;

}