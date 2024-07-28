package com.smilari.ejercitoargentino.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companias")
public class Compania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String actividad;

    @OneToMany(targetEntity = UserEntity.class, fetch = FetchType.LAZY, mappedBy = "compania")
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "compania_cuartel",
            joinColumns = @JoinColumn(name = "compania_id"),
            inverseJoinColumns = @JoinColumn(name = "cuartel_id")
    )
    private List<Cuartel> cuarteles;
}