package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taiKhoanId")
    private KhachHang khachHang;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "timeLine", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "timeLine-hoaDon")
    private Set<HoaDon> hoaDonSet;

}
