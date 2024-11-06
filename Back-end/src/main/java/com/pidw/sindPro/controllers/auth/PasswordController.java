package com.pidw.sindPro.controllers.auth;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.dtos.auth.PasswordResetDTO;
import com.pidw.sindPro.dtos.auth.PasswordResetRequestDTO;
import com.pidw.sindPro.repositories.UserRepository;
import com.pidw.sindPro.service.auth.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/password")
public class PasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/request-reset")
    public void requestResetPassword(@RequestBody PasswordResetRequestDTO request) {
        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = emailService.generateResetToken();
            user.setPasswordResetToken(token);
            userRepository.save(user);
            emailService.sendPasswordResetEmail(request.getEmail(), token);
        }
    }

    @Transactional
    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String token, @RequestBody PasswordResetDTO request) {
        Optional<User> userOptional = userRepository.findByPasswordResetToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setPasswordResetToken(null);
            userRepository.save(user);
        }
    }
}
