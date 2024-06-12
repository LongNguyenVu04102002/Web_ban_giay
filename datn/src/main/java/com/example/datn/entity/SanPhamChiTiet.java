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

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sanPhamChiTiet")
public class SanPhamChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sanPhamChiTietId", nullable = false)
    private Long sanPhamChiTietId;

    @Size(max = 20)
    @Column(name = "barCode", length = 20)
    private String barCode;

    @Column(name = "giaBan")
    private BigDecimal giaBan;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "trangThai")
    private boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kichThuocId")
    private KichThuoc kichThuoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mauSacId")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanPhamId")
    @JsonBackReference(value = "sanPhamChiTiet")
    private SanPham sanPham;

    @OneToMany(mappedBy = "sanPhamChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "sanPhamChiTiet")
    private List<HinhAnh> lstAnh;

    @OneToMany(mappedBy = "sanPhamChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "sanPhamChiTiet")
    private List<DanhGia> danhGiaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dotGiamGiaId")
    @JsonBackReference(value = "dotGiamGia")
    private DotGiamGia dotGiamGia;

}