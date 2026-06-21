package com.petcare.api.service;

import com.petcare.api.dto.request.ClienteRequest;
import com.petcare.api.dto.response.ClienteResponse;
import com.petcare.api.model.entity.Cliente;
import com.petcare.api.repository.ClienteRepository;
import com.petcare.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private PetService petService;

    public ClienteResponse cadastrar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setCpf(request.cpf());
        cliente.setAtivo(true);
        return ClienteResponse.fromEntity(clienteRepository.save(cliente));
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findByAtivoTrue()
                .stream().map(ClienteResponse::fromEntity).toList();
    }

    public ClienteResponse buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteResponse.fromEntity(cliente);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setCpf(request.cpf());
        return ClienteResponse.fromEntity(clienteRepository.save(cliente));
    }

    public void inativar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
        petService.inativarPorCliente(id);
    }

    public void ativar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(true);
        clienteRepository.save(cliente);
    }
}