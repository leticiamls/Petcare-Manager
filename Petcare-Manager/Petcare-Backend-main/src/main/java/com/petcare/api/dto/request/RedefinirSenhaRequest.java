package com.petcare.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RedefinirSenhaRequest(
        @NotBlank(message = "Token é obrigatório") String token,
        @NotBlank(message = "Nova senha é obrigatória") String novaSenha
) {}