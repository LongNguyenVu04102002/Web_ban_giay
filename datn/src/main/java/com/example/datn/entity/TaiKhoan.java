package com.example.datn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taiKhoan",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class TaiKhoan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taiKhoanId")
    private int id;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @Column(name = "ngayTao")
    private LocalDate ngayTao;

    @Column(name = "ngayCapNhap")
    private LocalDate ngayCapNhap;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "taiKhoanVaiTro",
            joinColumns = @JoinColumn(name = "taiKhoanId"),
            inverseJoinColumns = @JoinColumn(name = "vaiTroId"))
    private List<VaiTro> vaiTros;

}
