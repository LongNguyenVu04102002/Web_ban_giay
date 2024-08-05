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
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nhanVien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nhanVienId", nullable = false)
    private Long nhanVienId;

    @Column(name = "maNhanVien", length = 100)
    private String maNhanVien;

    @Column(name = "hoTen", length = 100)
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "xa", length = 20)
    private String xa;

    @Column(name = "huyen", length = 20)
    private String huyen;

    @Column(name = "thanhPho", length = 20)
    private String thanhPho;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "hoaDon")
    private List<HoaDon> hoaDonList;

}
