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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "khachHang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khachHangId", nullable = false)
    private Long khachHangId;

    @Column(name = "hoTen", length = 100)
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    @NotEmpty(message = "Họ tên không được để trống")
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    @NotNull(message = "khong dc de trong")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 20)

    private String sdt;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email không được vượt quá 50 ký tự")
    @Email(message = "Email không hợp lệ")
    @NotNull(message = "khong dc de trong")
    @NotEmpty(message = " không được để trống")
    private String email;



    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "trangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "khachHang")
    private List<DiaChi> diaChiList;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "khachHang")
    private List<DanhGia> danhGiaList;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "khachHang")
    private List<HoaDon> hoaDonList;

}