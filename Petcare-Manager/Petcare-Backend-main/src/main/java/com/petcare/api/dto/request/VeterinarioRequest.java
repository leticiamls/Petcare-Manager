package com.petcare.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VeterinarioRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "CRMV é obrigatório") String crmv,
        @NotBlank(message = "Telefone é obrigatório") String telefone
) {}