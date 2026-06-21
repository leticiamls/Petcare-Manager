package com.petcare.api.dto.request;

import com.petcare.api.model.enums.RoleUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotBlank(message = "Username é obrigatório") String username,
        @NotBlank(message = "Senha é obrigatória") String password,
        @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
        @NotNull(message = "Role é obrigatória") RoleUsuario role,
        Long veterinarioId
) {}