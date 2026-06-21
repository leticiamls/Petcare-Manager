package com.petcare.api.dto.response;

import com.petcare.api.model.entity.Veterinario;

public record VeterinarioResponse(
        Long id,
        String nome,
        String crmv,
        String telefone,
        Boolean ativo
) {
    public static VeterinarioResponse fromEntity(Veterinario vet) {
        return new VeterinarioResponse(
                vet.getId(),
                vet.getNome(),
                vet.getCrmv(),
                vet.getTelefone(),
                vet.getAtivo()
        );
    }
}