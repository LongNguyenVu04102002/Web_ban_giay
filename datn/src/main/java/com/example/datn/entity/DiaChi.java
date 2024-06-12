package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "diaChi")
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaChiId", nullable = false)
    private Long diaChiId;

    @Lob
    @Column(name = "diaChiNhan")
    private String diaChiNhan;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "hoTen", length = 100)
    private String hoTen;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "xa", length = 20)
    private String xa;

    @Column(name = "huyen", length = 20)
    private String huyen;

    @Column(name = "thanhPho", length = 20)
    private String thanhPho;

    @ManyToOne
    @JoinColumn(name = "khachHangId")
    @JsonBackReference(value = "khachHang")
    private KhachHang khachHang;

}