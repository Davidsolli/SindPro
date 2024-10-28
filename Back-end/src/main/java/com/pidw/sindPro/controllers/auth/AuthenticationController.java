package com.pidw.sindPro.controllers.auth;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.dtos.auth.LoginDTO;
import com.pidw.sindPro.dtos.auth.LoginResponseDTO;
import com.pidw.sindPro.dtos.auth.RegisterDTO;
import com.pidw.sindPro.infra.security.TokenService;
import com.pidw.sindPro.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByEmail(registerDTO.getEmail()) != null) return ResponseEntity.badRequest().build();
        User user = new User();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        user.setPassword(encryptedPassword);
        copyDtoToEntity(user, registerDTO);
        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    private void copyDtoToEntity(User user, RegisterDTO registerDTO) {
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setCpf(registerDTO.getCpf());
        user.setAddress(registerDTO.getAddress());
        user.setApartment(registerDTO.getApartment());
        user.setUserRole(registerDTO.getUserRole()); // <- temporario
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }
}
