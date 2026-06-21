package com.petcare.api.service;

import com.petcare.api.model.entity.Usuario;
import com.petcare.api.model.enums.RoleUsuario;
import com.petcare.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@petcare.com");
            admin.setRole(RoleUsuario.ADMIN);
            admin.setAtivo(true);
            usuarioRepository.save(admin);
            System.out.println("Admin padrão criado: username=admin, senha=admin123");
        }
    }
}