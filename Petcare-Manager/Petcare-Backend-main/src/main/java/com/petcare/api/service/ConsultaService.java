package com.petcare.api.service;

import com.petcare.api.dto.request.ConsultaRequest;
import com.petcare.api.dto.response.ConsultaResponse;
import com.petcare.api.exception.BusinessException;
import com.petcare.api.exception.ResourceNotFoundException;
import com.petcare.api.model.entity.Consulta;
import com.petcare.api.model.entity.Medicamento;
import com.petcare.api.model.entity.Pet;
import com.petcare.api.model.entity.Sintoma;
import com.petcare.api.model.entity.Veterinario;
import com.petcare.api.model.enums.StatusConsulta;
import com.petcare.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired private ConsultaRepository consultaRepository;
    @Autowired private PetRepository petRepository;
    @Autowired private VeterinarioRepository veterinarioRepository;
    @Autowired private SintomaRepository sintomaRepository;
    @Autowired private MedicamentoRepository medicamentoRepository;

    public ConsultaResponse abrir(ConsultaRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));
        Veterinario vet = veterinarioRepository.findById(request.veterinarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setPet(pet);
        consulta.setVeterinario(vet);
        consulta.setData(request.data());
        consulta.setDescricao(request.descricao());
        consulta.setDiagnostico(request.diagnostico());
        consulta.setExames(request.exames());
        consulta.setStatus(StatusConsulta.ABERTA);
        consulta.setSintomas(resolverSintomas(request.sintomas()));
        consulta.setMedicamentos(resolverMedicamentos(request.medicamentos()));

        return ConsultaResponse.fromEntity(consultaRepository.save(consulta));
    }

    public List<ConsultaResponse> listar(Long veterinarioId) {
        if (veterinarioId != null) {
            return consultaRepository.findByVeterinario_Id(veterinarioId)
                    .stream().map(ConsultaResponse::fromEntity).toList();
        }
        return consultaRepository.findAll()
                .stream().map(ConsultaResponse::fromEntity).toList();
    }

    public List<ConsultaResponse> listarPorPet(Long petId) {
        return consultaRepository.findByPet_Id(petId)
                .stream().map(ConsultaResponse::fromEntity).toList();
    }

    public ConsultaResponse atualizar(Long id, ConsultaRequest request) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));

        if (consulta.getStatus() != StatusConsulta.ABERTA) {
            throw new BusinessException("Consulta finalizada ou cancelada não pode ser alterada");
        }

        consulta.setDescricao(request.descricao());
        consulta.setDiagnostico(request.diagnostico());
        consulta.setExames(request.exames());
        consulta.setSintomas(resolverSintomas(request.sintomas()));
        consulta.setMedicamentos(resolverMedicamentos(request.medicamentos()));

        return ConsultaResponse.fromEntity(consultaRepository.save(consulta));
    }

    public void finalizar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        if (consulta.getStatus() != StatusConsulta.ABERTA) {
            throw new BusinessException("Consulta finalizada ou cancelada não pode ser alterada");
        }
        consulta.setStatus(StatusConsulta.FINALIZADA);
        consultaRepository.save(consulta);
    }

    public void cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        if (consulta.getStatus() != StatusConsulta.ABERTA) {
            throw new BusinessException("Consulta já finalizada ou cancelada");
        }
        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }

    private List<Sintoma> resolverSintomas(List<String> nomes) {
        if (nomes == null) return new ArrayList<>();
        List<Sintoma> resultado = new ArrayList<>();
        for (String nome : nomes) {
            Sintoma sintoma = sintomaRepository.findByNomeIgnoreCase(nome)
                    .orElseGet(() -> {
                        Sintoma novo = new Sintoma();
                        novo.setNome(nome);
                        return sintomaRepository.save(novo);
                    });
            resultado.add(sintoma);
        }
        return resultado;
    }

    private List<Medicamento> resolverMedicamentos(List<String> nomes) {
        if (nomes == null) return new ArrayList<>();
        List<Medicamento> resultado = new ArrayList<>();
        for (String nome : nomes) {
            Medicamento medicamento = medicamentoRepository.findByNomeIgnoreCase(nome)
                    .orElseGet(() -> {
                        Medicamento novo = new Medicamento();
                        novo.setNome(nome);
                        novo.setDose("");
                        return medicamentoRepository.save(novo);
                    });
            resultado.add(medicamento);
        }
        return resultado;
    }
}