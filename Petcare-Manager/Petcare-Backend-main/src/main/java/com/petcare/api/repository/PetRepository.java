package com.petcare.api.repository;

import com.petcare.api.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAtivoTrue();
    List<Pet> findByClienteId(Long clienteId);
    List<Pet> findByClienteIdAndAtivoTrue(Long clienteId);
}