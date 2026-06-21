package com.petcare.api.repository;

import com.petcare.api.model.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findByNomeIgnoreCase(String nome);
}