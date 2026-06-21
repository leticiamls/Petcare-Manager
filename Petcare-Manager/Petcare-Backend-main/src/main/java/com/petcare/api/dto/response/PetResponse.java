package com.petcare.api.dto.response;

import com.petcare.api.model.entity.Pet;

public record PetResponse(
        Long id,
        String nome,
        String especie,
        String raca,
        String dataNascimento,
        Boolean ativo,
        Long clienteId
) {
    public static PetResponse fromEntity(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getDataNascimento() != null ? pet.getDataNascimento().toString() : null,
                pet.getAtivo(),
                pet.getCliente().getId()
        );
    }
}