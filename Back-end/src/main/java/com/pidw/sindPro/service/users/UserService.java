package com.pidw.sindPro.service.users;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.dtos.users.UserDTO;
import com.pidw.sindPro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserDTO findMe() {
        User user = getAuthenticatedUser();
        return new UserDTO(user);
    }
}
