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

    @Column(name = "hoTen", length = 100)
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    @NotEmpty(message = "Họ tên không được để trống")
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 20)
    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    @Pattern(regexp = "^\\d{10,20}$", message = "Số điện thoại không hợp lệ")
    private String sdt;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email không được vượt quá 50 ký tự")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(name = "matKhau")
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String matKhau;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "role")
//    @NotEmpty(message = "Vai trò không được để trống")
    private String role;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "nhanVien-hoaDon")
    private List<HoaDon> hoaDonList;
}
