package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "hoaDonChiTiet")
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoaDonChiTietId", nullable = false)
    private Long hoaDonChiTietId;

    @Column(name = "donGia", precision = 19, scale = 4)
    private BigDecimal donGia;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "trangThai")
    private Integer trangThai;

    @Lob
    @Column(name = "ghiChu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "hoaDonId")
    @JsonBackReference(value = "hoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "sanPhamChiTietId")
    private SanPhamChiTiet sanPhamChiTiet;

}