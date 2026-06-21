package com.petcare.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PetRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Espécie é obrigatória") String especie,
        String raca,
        LocalDate dataNascimento,
        @NotNull(message = "Cliente é obrigatório") Long clienteId
) {}