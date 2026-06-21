package com.petcare.api.controller;

import com.petcare.api.model.entity.Sintoma;
import com.petcare.api.service.SintomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sintomas")
@CrossOrigin(origins = "*")
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;

    @GetMapping
    public ResponseEntity<List<Sintoma>> listar() {
        return ResponseEntity.ok(sintomaService.listar());
    }
}