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
@Table(name = "cuarteles")
public class Cuartel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @ManyToMany(mappedBy = "cuarteles")
    private List<Compania> companias;

    @OneToMany(targetEntity = UserEntity.class, mappedBy = "cuartel", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}