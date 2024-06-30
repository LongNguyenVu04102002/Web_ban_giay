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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

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
    @Column(name = "maVanDon", length = 100)
    private String maVanDon;

    @Column(name = "phiShip")
    private BigDecimal phiShip;

    @Column(name = "tienGiam")
    private BigDecimal tienGiam;

    @Column(name = "tongTien")
    private BigDecimal tongTien;

    @Column(name = "loaiHoaDon")
    private boolean loaiHoaDon;

    @Size(max = 3000)
    @Column(name = "moTa", length = 3000)
    private String moTa;

    @Column(name = "email")
    private String email;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Size(max = 100)
    @Column(name = "tenNguoiNhan", length = 100)
    private String tenNguoiNhan;

    @Column(name = "sdtNhan")
    private String sdtNhan;

    @Size(max = 100)
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

    @OneToMany(mappedBy = "hoaDon",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTietList;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "hoaDon")
    private List<HinhThucThanhToan> hinhThucThanhToanList;

    @OneToMany(mappedBy = "hoaDon",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TimeLine> timeLineList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "phieuGiamGiaId")
    @JsonBackReference
    private PhieuGiamGia phieuGiamGia;

}
