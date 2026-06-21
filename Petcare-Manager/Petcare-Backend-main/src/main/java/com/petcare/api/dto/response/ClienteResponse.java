package com.petcare.api.dto.response;

import com.petcare.api.model.entity.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String telefone,
        String cpf,
        Boolean ativo
) {
    public static ClienteResponse fromEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getAtivo()
        );
    }
}