package com.petcare.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String dose;
}