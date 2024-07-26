package com.smilari.ejercitoargentino.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToOne(targetEntity = CuerpoEjercito.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cuerpo_ejercito_id")
    private CuerpoEjercito cuerpoEjercito;

    @ManyToOne(targetEntity = Compania.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "compania_id")
    private Compania compania;

    @ManyToOne(targetEntity = Cuartel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cuartel_id")
    private Cuartel cuartel;

    @OneToMany(targetEntity = SoldadoServicio.class, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SoldadoServicio> soldadoServicios;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
        return true;
    }
}
