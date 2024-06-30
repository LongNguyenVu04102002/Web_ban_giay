package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
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
@Table(name = "timeLine")
public class TimeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeLineId", nullable = false)
    private Long timeLineId;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "ngayShip")
    private LocalDate ngayShip;

    @Column(name = "ngayNhan")
    private LocalDate ngayNhan;

    @Column(name = "ngayThanhToan")
    private LocalDate ngayThanhToan;

    @Column(name = "ngayHuy")
    private LocalDate ngayHuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taiKhoanId")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hoaDonId")
    @JsonBackReference
    private HoaDon hoaDon;

}
