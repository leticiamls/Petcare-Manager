package com.petcare.api.controller;

import com.petcare.api.dto.request.ConsultaRequest;
import com.petcare.api.dto.response.ConsultaResponse;
import com.petcare.api.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<ConsultaResponse> abrir(@RequestBody @Valid ConsultaRequest request) {
        return ResponseEntity.status(201).body(consultaService.abrir(request));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> listar(
            @RequestParam(required = false) Long veterinarioId) {
        return ResponseEntity.ok(consultaService.listar(veterinarioId));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ConsultaResponse>> listarPorPet(@PathVariable Long petId) {
        return ResponseEntity.ok(consultaService.listarPorPet(petId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VET')")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable Long id,
                                                      @RequestBody @Valid ConsultaRequest request) {
        return ResponseEntity.ok(consultaService.atualizar(id, request));
    }

    @PatchMapping("/finalizar/{id}")
    @PreAuthorize("hasRole('VET')")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        consultaService.finalizar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/cancelar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}