package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "danhGia")
public class DanhGia {

    @Id
    @Column(name = "danhGiaId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long danhGiaId;

    @Column(name = "saoDanhGia")
    private Integer saoDanhGia;

    @Column(name = "binhLuan")
    private String binhLuan;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "thoiGian")
    private LocalDate thoiGian;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khachHangId")
    @JsonBackReference(value = "khachHang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanPhamChiTietId")
    @JsonBackReference(value = "sanPhamChiTiet")
    private SanPhamChiTiet sanPhamChiTiet;

}
