package com.petcare.api.controller;

import com.petcare.api.dto.request.LoginRequest;
import com.petcare.api.dto.request.RecuperarSenhaRequest;
import com.petcare.api.dto.request.RedefinirSenhaRequest;
import com.petcare.api.dto.response.LoginResponse;
import com.petcare.api.repository.UsuarioRepository;
import com.petcare.api.service.TokenService;
import com.petcare.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                request.username(), request.password()
        );
        authenticationManager.authenticate(usernamePassword);

        var usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponse(
                token,
                usuario.getUsername(),
                usuario.getRole().name(),
                usuario.getVeterinarioId()
        ));
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Void> recuperarSenha(@RequestBody @Valid RecuperarSenhaRequest request) {
        usuarioService.recuperarSenha(request.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@RequestBody @Valid RedefinirSenhaRequest request) {
        usuarioService.redefinirSenha(request.token(), request.novaSenha());
        return ResponseEntity.ok().build();
    }
}