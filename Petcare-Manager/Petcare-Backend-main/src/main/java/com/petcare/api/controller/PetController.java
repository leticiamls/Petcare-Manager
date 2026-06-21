package com.petcare.api.controller;

import com.petcare.api.dto.request.PetRequest;
import com.petcare.api.dto.response.PetResponse;
import com.petcare.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> cadastrar(@RequestBody @Valid PetRequest request) {
        return ResponseEntity.status(201).body(petService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> listar() {
        return ResponseEntity.ok(petService.listar());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PetResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(petService.listarPorCliente(clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> atualizar(@PathVariable Long id,
                                                 @RequestBody @Valid PetRequest request) {
        return ResponseEntity.ok(petService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        petService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        petService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}