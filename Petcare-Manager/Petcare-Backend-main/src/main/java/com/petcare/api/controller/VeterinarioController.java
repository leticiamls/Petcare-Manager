package com.petcare.api.controller;

import com.petcare.api.dto.request.VeterinarioRequest;
import com.petcare.api.dto.response.VeterinarioResponse;
import com.petcare.api.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioResponse> cadastrar(@RequestBody @Valid VeterinarioRequest request) {
        return ResponseEntity.status(201).body(veterinarioService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioResponse>> listar() {
        return ResponseEntity.ok(veterinarioService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid VeterinarioRequest request) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        veterinarioService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        veterinarioService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}