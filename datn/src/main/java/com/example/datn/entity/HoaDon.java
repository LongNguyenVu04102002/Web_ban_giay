package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "hoaDon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoaDonId")
    private Long hoaDonId;

    @Size(max = 100)
    @Nationalized
    @Column(name = "maVanDon", length = 100)
    private String maVanDon;

    @Column(name = "phiShip")
    private BigDecimal phiShip;

    @Column(name = "tienGiam")
    private BigDecimal tienGiam;

    @Column(name = "loaiHoaDon")
    private boolean loaiHoaDon;

    @Size(max = 3000)
    @Column(name = "moTa", length = 3000)
    private String moTa;

    @Column(name = "email")
    private String email;

    @Size(max = 100)
    @Nationalized
    @Column(name = "tenNguoiNhan", length = 100)
    private String tenNguoiNhan;

    @Column(name = "sdtNhan")
    private String sdtNhan;

    @Size(max = 100)
    @Nationalized
    @Column(name = "diaChiNhan", length = 100)
    private String diaChiNhan;

    @ManyToOne
    @JoinColumn(name = "khachHangId")
    @JsonBackReference(value = "khachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhanVienId")
    @JsonBackReference(value = "nhanVien-hoaDon")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "hoaDonChiTiet-hoaDon")
    private Set<HoaDonChiTiet> hoaDonChiTietSet;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "hinhThucThanhToan-hoaDon")
    private Set<HinhThucThanhToan> hinhThucThanhToanSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeLineId")
    @JsonBackReference(value = "timeLine-hoaDon")
    private TimeLine timeLine;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phieuGiamGiaId")
    private PhieuGiamGia phieuGiamGia;

}
