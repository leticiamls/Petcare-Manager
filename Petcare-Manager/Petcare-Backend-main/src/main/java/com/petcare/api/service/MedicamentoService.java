package com.petcare.api.service;

import com.petcare.api.model.entity.Medicamento;
import com.petcare.api.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired private MedicamentoRepository medicamentoRepository;

    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }
}