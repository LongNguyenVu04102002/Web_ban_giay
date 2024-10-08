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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nhanVien")
public class NhanVien implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nhanVienId", nullable = false)
    private Long nhanVienId;

    @Column(name = "maNhanVien", length = 100)
    private String maNhanVien;

    @Column(name = "hoTen", length = 100)
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    @Pattern(regexp = "^[\\p{L} \\s]*$", message = "Họ tên chỉ được chứa chữ cái và khoảng trắng")
    @Pattern(regexp = "^(?!\\s).*$", message = "Họ tên không được bắt đầu bằng khoảng trắng")
    @Pattern(regexp = ".*\\S$", message = "Họ tên không được kết thúc bằng khoảng trắng")
    @Pattern(regexp = "^(?!.*\\s{2,}).*$", message = "Họ tên không được chứa nhiều khoảng trắng liên tiếp")
    private String hoTen;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    @NotNull(message = "Không được để trống")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 20)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    @Pattern(regexp = "^0\\d{9,19}$", message = "Số điện thoại phải bắt đầu bằng số 0 và chỉ chứa các chữ số")
    private String sdt;

    @Column(name = "email", length = 50)
    @Size(max = 30, message = "Email không được vượt quá 30 ký tự")
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email không hợp lệ")
    private String email;

    @Column(name = "password")
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> role.toUpperCase());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return trangThai;
    }


}
