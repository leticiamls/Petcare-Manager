package com.petcare.api.dto.response;

import com.petcare.api.model.entity.Usuario;

public record UsuarioResponse(
        Long id,
        String username,
        String email,
        String role,
        Boolean ativo,
        Long veterinarioId
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole().name(),
                usuario.getAtivo(),
                usuario.getVeterinarioId()
        );
    }
}