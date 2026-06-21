package com.petcare.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_veterinario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crmv;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Boolean ativo = true;
}