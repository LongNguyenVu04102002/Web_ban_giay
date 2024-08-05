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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Giá bán không được để trống")
    @Min(value = 1000, message = "Giá bán phải lớn hơn hoặc bằng 1000")
    @Positive(message = "Giá bán phải là số dương")
    @Column(name = "giaBan")
    private BigDecimal giaBan;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    @Positive(message = "Số lượng phải là số dương")
    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "trangThai")
    private boolean trangThai;

    @NotNull(message = "Kích thước không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kichThuocId")
    private KichThuoc kichThuoc;

    @NotNull(message = "Màu sắc không được để trống")
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

}