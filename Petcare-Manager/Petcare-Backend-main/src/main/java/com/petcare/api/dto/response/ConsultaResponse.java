package com.petcare.api.dto.response;

import com.petcare.api.model.entity.Consulta;
import com.petcare.api.model.entity.Medicamento;
import com.petcare.api.model.entity.Sintoma;

import java.util.List;

public record ConsultaResponse(
        Long id,
        String data,
        String descricao,
        String diagnostico,
        String exames,
        String status,
        Long petId,
        Long veterinarioId,
        List<String> sintomas,
        List<String> medicamentos
) {
    public static ConsultaResponse fromEntity(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getData().toString(),
                consulta.getDescricao(),
                consulta.getDiagnostico(),
                consulta.getExames(),
                consulta.getStatus().name(),
                consulta.getPet().getId(),
                consulta.getVeterinario().getId(),
                consulta.getSintomas().stream().map(Sintoma::getNome).toList(),
                consulta.getMedicamentos().stream().map(Medicamento::getNome).toList()
        );
    }
}