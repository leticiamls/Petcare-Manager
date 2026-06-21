package com.petcare.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailRemetente;

    public void enviarEmailRecuperacao(String emailDestino, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailRemetente);
        message.setTo(emailDestino);
        message.setSubject("Redefinição de senha — PetCare Manager");
        message.setText(
                "Olá!\n\n" +
                        "Recebemos uma solicitação para redefinir sua senha.\n\n" +
                        "Clique no link abaixo para redefinir:\n" +
                        "http://localhost:5173/redefinir-senha?token=" + token + "\n\n" +
                        "Este link expira em 15 minutos.\n\n" +
                        "Se não foi você, ignore este e-mail.\n\n" +
                        "PetCare Manager"
        );
        mailSender.send(message);
    }
}