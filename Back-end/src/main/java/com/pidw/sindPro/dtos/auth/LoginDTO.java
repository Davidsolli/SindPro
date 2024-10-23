package com.pidw.sindPro.dtos.auth;

import com.pidw.sindPro.domains.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }
}
