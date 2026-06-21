package com.petcare.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_sintoma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}