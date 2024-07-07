package com.example.datn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "lotGiay")
public class LotGiay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotGiayId", nullable = false)
    private Long lotGiayId;

    @NotEmpty(message = "Tên không được trống!")
    @Size(max = 100)
    @Nationalized
    @Column(name = "ten", length = 100)
    private String ten;

    @Size(max = 3000)
    @Nationalized
    @Column(name = "moTa", length = 3000)
    private String moTa;

    @Column(name = "trangThai")
    private boolean trangThai;

}