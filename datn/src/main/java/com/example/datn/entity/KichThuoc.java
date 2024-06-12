package com.example.datn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "kichThuoc")
public class KichThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kichThuocId", nullable = false)
    private Long kichThuocId;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten", length = 100)
    private int ten;

    @Size(max = 300)
    @Nationalized
    @Column(name = "moTa", length = 300)
    private String moTa;

    @Column(name = "trangThai")
    private boolean trangThai;

}