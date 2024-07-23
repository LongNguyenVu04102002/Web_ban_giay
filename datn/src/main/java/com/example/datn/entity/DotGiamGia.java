package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "dotGiamGia")
public class DotGiamGia {

    @Id
    @Column(name = "dotGiamGiaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dotGiamGiaId;

    @Column(name = "ten", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String ten;

    @Column(name = "ngayBatDau", columnDefinition = "DATETIME", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc", columnDefinition = "DATETIME", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "trangThai", columnDefinition = "INT", nullable = false)
    private boolean trangThai;

    @Column(name = "ghiChu", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String ghiChu;

    @Column(name = "phanTramGiam", columnDefinition = "INT", nullable = false)
    private Integer phanTramGiam;

    @OneToMany(mappedBy = "dotGiamGia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "dotGiamGia")
    private Set<SanPhamChiTiet> sanPhamChiTietSet;

}