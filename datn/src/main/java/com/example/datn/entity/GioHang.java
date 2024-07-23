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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gioHang")
public class GioHang {

    @Id
    @Column(name = "gioHangId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gioHangId;

    @ManyToOne
    @JoinColumn(name = "khachHangId")
    private KhachHang khachHang;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "trangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "gioHang",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "gioHang")
    private List<GioHangChiTiet> gioHangChiTietList;

}
