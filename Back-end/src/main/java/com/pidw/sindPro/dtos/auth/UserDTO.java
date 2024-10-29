package com.pidw.sindPro.dtos.auth;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.domains.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String cpf;
    private String address;
    private Integer apartment;
    private UserRole userRole;

    private RegisterDTO(User user) {
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        phoneNumber = user.getPhoneNumber();
        cpf = user.getCpf();
        address = user.getAddress();
        apartment = user.getApartment();
    }
}
