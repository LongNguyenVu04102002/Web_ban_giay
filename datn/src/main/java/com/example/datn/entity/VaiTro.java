package com.example.datn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaiTro")
public class VaiTro {

    @Id
    @Column(name = "vaiTroId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaiTroId;

    @Enumerated(EnumType.STRING)
    @Column(name = "vaiTroName", length = 20)
    private ERole name;

}
