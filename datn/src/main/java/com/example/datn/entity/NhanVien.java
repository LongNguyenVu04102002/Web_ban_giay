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
    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 20)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải có 10 chữ số")
    private String sdt;

    @Column(name = "email", length = 50)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
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

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "hoaDon")
    private List<HoaDon> hoaDonList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taiKhoanId")
    private TaiKhoan taiKhoan;

}
