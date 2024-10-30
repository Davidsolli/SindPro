package com.pidw.sindPro.service.users;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.dtos.users.UserRegisterDTO;
import com.pidw.sindPro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getAuthenticatedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) return userDetails.getUsername();
        throw new RuntimeException("Usuário autenticado não encontrado");
    }

    public User getAuthenticatedUser() {
        String email = getAuthenticatedUserEmail();
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    @Transactional(readOnly = true)
    public UserRegisterDTO findMe() {
        User user = getAuthenticatedUser();
        return new UserRegisterDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserRegisterDTO> findAll() {
        List<User> result = userRepository.findAll();
        return result.stream().map(UserRegisterDTO::new).toList();
    }
}
