package com.smilari.ejercitoargentino.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "cuerpos_ejercito")
public class CuerpoEjercito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String denominacion;

    @OneToMany(targetEntity = UserEntity.class, fetch = FetchType.LAZY, mappedBy = "cuerpoEjercito")
    private List<UserEntity> users;
}
