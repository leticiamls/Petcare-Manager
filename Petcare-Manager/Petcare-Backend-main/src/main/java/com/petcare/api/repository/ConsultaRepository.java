package com.petcare.api.repository;

import com.petcare.api.model.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPet_Id(Long petId);
    List<Consulta> findByVeterinario_Id(Long veterinarioId);
}