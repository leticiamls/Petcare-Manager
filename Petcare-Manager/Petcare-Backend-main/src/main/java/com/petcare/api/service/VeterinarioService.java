package com.petcare.api.service;

import com.petcare.api.dto.request.VeterinarioRequest;
import com.petcare.api.dto.response.VeterinarioResponse;
import com.petcare.api.model.entity.Veterinario;
import com.petcare.api.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    @Autowired private VeterinarioRepository veterinarioRepository;

    public VeterinarioResponse cadastrar(VeterinarioRequest request) {
        Veterinario vet = new Veterinario();
        vet.setNome(request.nome());
        vet.setCrmv(request.crmv());
        vet.setTelefone(request.telefone());
        vet.setAtivo(true);
        return VeterinarioResponse.fromEntity(veterinarioRepository.save(vet));
    }

    public List<VeterinarioResponse> listar() {
        return veterinarioRepository.findByAtivoTrue()
                .stream().map(VeterinarioResponse::fromEntity).toList();
    }

    public VeterinarioResponse atualizar(Long id, VeterinarioRequest request) {
        Veterinario vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        vet.setNome(request.nome());
        vet.setCrmv(request.crmv());
        vet.setTelefone(request.telefone());
        return VeterinarioResponse.fromEntity(veterinarioRepository.save(vet));
    }

    public void inativar(Long id) {
        Veterinario vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        vet.setAtivo(false);
        veterinarioRepository.save(vet);
    }

    public void ativar(Long id) {
        Veterinario vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        vet.setAtivo(true);
        veterinarioRepository.save(vet);
    }
}