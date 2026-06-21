package com.petcare.api.service;

import com.petcare.api.dto.request.PetRequest;
import com.petcare.api.dto.response.PetResponse;
import com.petcare.api.model.entity.Cliente;
import com.petcare.api.model.entity.Pet;
import com.petcare.api.model.enums.StatusConsulta;
import com.petcare.api.repository.ClienteRepository;
import com.petcare.api.repository.ConsultaRepository;
import com.petcare.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired private PetRepository petRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ConsultaRepository consultaRepository;

    public PetResponse cadastrar(PetRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Pet pet = new Pet();
        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setDataNascimento(request.dataNascimento());
        pet.setCliente(cliente);
        pet.setAtivo(true);
        return PetResponse.fromEntity(petRepository.save(pet));
    }

    public List<PetResponse> listar() {
        return petRepository.findAll()
                .stream().map(PetResponse::fromEntity).toList();
    }

    public List<PetResponse> listarPorCliente(Long clienteId) {
        return petRepository.findByClienteIdAndAtivoTrue(clienteId)
                .stream().map(PetResponse::fromEntity).toList();
    }

    public PetResponse atualizar(Long id, PetRequest request) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setDataNascimento(request.dataNascimento());
        return PetResponse.fromEntity(petRepository.save(pet));
    }

    public void inativar(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        pet.setAtivo(false);
        petRepository.save(pet);
        cancelarConsultasAbertas(id);
    }

    public void ativar(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        pet.setAtivo(true);
        petRepository.save(pet);
    }

    public void inativarPorCliente(Long clienteId) {
        petRepository.findByClienteId(clienteId).forEach(pet -> {
            pet.setAtivo(false);
            petRepository.save(pet);
            cancelarConsultasAbertas(pet.getId());
        });
    }

    private void cancelarConsultasAbertas(Long petId) {
        consultaRepository.findByPet_Id(petId).forEach(consulta -> {
            if (consulta.getStatus() == StatusConsulta.ABERTA) {
                consulta.setStatus(StatusConsulta.CANCELADA);
                consultaRepository.save(consulta);
            }
        });
    }
}