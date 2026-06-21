package com.petcare.api.dto.request;

import java.time.LocalDate;
import java.util.List;

public record ConsultaRequest(
        Long petId,
        Long veterinarioId,
        LocalDate data,
        String descricao,
        String diagnostico,
        String exames,
        List<String> sintomas,
        List<String> medicamentos
) {}