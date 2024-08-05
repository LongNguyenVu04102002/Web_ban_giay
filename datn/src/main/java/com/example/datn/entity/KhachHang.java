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
//sadasadsdsdsdsd
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khachHangId", nullable = false)
    private Long khachHangId;

<<<<<<< HEAD
    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Họ tên chỉ được chứa chữ cái và khoảng trắng")

    @Pattern(regexp = "^[\\p{L} \\s]*$", message = "Họ tên chỉ được chứa chữ cái và khoảng trắng")
<<<<<<< HEAD
    @Pattern(regexp = "^(?!\\s).*$", message = "Họ tên không được bắt đầu bằng khoảng trắng")
    @Pattern(regexp = ".*\\S$", message = "Họ tên không được kết thúc bằng khoảng trắng")
    @Pattern(regexp = "^(?!.*\\s{2,}).*$", message = "Họ tên không được chứa nhiều khoảng trắng liên tiếp")
<<<<<<< HEAD

=======

=======
//    @NotBlank(message = "Họ tên không được để trống")
//    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
//    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Họ tên chỉ được chứa chữ cái và khoảng trắng")
>>>>>>> 7ad8ffbb5f6e88af3108c968d7cd0797e69ce7dd
>>>>>>> parent of f4e9d10 (update)
=======
=======
>>>>>>> master
>>>>>>> parent of f6893b4 (no message)
    @Column(name = "hoTen", length = 100)
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
//    @NotNull(message = "Không được để trống")
    private LocalDate ngaySinh;

<<<<<<< HEAD
    @NotBlank(message = "Số điện thoại không được để trống")

    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")

    @Pattern(regexp = "^\\d+$", message = "Số điện thoại chỉ chứa các chữ số")

    @Pattern(regexp = "^0\\d{9,19}$", message = "Số điện thoại phải bắt đầu bằng số 0 và chỉ chứa các chữ số")


    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu bằng số 0 và chứa đúng 10 chữ số")

<<<<<<< HEAD

=======
=======
//    @NotBlank(message = "Số điện thoại không được để trống")
//    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
//    @Pattern(regexp = "^\\d+$", message = "Số điện thoại chỉ chứa các chữ số")
>>>>>>> master
>>>>>>> parent of f6893b4 (no message)
    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "email")
//    @NotNull(message = "Không được để trống")
    private String email;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "trangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DiaChi> diaChiList;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "hoaDon")
    private List<HoaDon> hoaDonList;



}