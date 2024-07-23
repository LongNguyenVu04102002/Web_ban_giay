package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "gioHangChiTiet")
public class GioHangChiTiet {

    @Id
    @Column(name = "gioHangChiTietId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gioHangChiTietId;

    @ManyToOne
    @JoinColumn(name = "gioHangId")
    @JsonBackReference(value = "gioHang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "sanPhamChiTietId")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;
}
