package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sanPhamId")
//    @JsonBackReference
    private SanPham sanPham;

    @OneToMany(mappedBy = "sanPhamChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "sanPhamChiTiet")
    private List<HinhAnh> lstAnh;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dotGiamGiaId")
//    @JsonBackReference(value = "dotGiamGia")
//    private DotGiamGia dotGiamGia;

    @Transient
    private String base64Image;

}