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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sanPham")
public class SanPham {

    @Id
    @Column(name = "sanPhamId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sanPhamId;

    @NotEmpty(message = "Tên không được trống!")
    @Column(name = "ten")
    private String ten;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "moTa")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "deGiayId")
    private DeGiay deGiay;

    @ManyToOne
    @JoinColumn(name = "thuongHieuId")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "coGiayId")
    private CoGiay coGiay;

    @ManyToOne
    @JoinColumn(name = "lotGiayId")
    private LotGiay lotGiay;

    @ManyToOne
    @JoinColumn(name = "muiGiayId")
    private MuiGiay muiGiay;

    @ManyToOne
    @JoinColumn(name = "chatLieuId")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "dayGiayId")
    private DayGiay dayGiay;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SanPhamChiTiet> sanPhamChiTietList;

}
