package com.pidw.sindPro.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://localhost:4200/reset-password-form?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reset de senha");
        message.setText("Para redefinir sua senha, clique no link a seguir: " + resetUrl);
        javaMailSender.send(message);
    }

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
