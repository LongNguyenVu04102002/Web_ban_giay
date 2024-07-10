package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "phieuGiamGia")
public class PhieuGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phieuGiamGiaId")
    private Long phieuGiamGiaId;

    @Size(max = 10)
    @Column(name = "maGiamGia")
    private String maGiamGia;

    @Column(name = "giaTriGiam")
    private Integer giaTriGiam;

    @Column(name = "loaiPhieu")
    private boolean loaiPhieu;

    @Column(name = "soLuongPhieu")
    private Integer soLuongPhieu;

    @Column(name = "ngayBatDau")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThuc;

    @Column(name = "giaTriDonToiThieu", precision = 18)
    private BigDecimal giaTriDonToiThieu;

    @Column(name = "giaTriGiamToiDa", precision = 18)
    private BigDecimal giaTriGiamToiDa;

    @Column(name = "trangThai")
    private int trangThai;

    @OneToMany(mappedBy = "phieuGiamGia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<HoaDon> hoaDonList;

}