package com.petcare.api.service;

import com.petcare.api.model.entity.Sintoma;
import com.petcare.api.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SintomaService {

    @Autowired private SintomaRepository sintomaRepository;

    public List<Sintoma> listar() {
        return sintomaRepository.findAll();
    }
}