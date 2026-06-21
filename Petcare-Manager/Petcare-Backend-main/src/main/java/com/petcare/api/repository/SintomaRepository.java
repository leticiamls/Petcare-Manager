package com.petcare.api.repository;

import com.petcare.api.model.entity.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SintomaRepository extends JpaRepository<Sintoma, Long> {
    Optional<Sintoma> findByNomeIgnoreCase(String nome);
}