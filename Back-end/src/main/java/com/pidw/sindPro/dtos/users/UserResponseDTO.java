package com.pidw.sindPro.dtos.users;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.domains.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String address;
    private Integer apartment;
    private UserRole userRole;

    public UserResponseDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        cpf = user.getCpf();
        address = user.getAddress();
        apartment = user.getApartment();
        userRole = user.getUserRole();
    }
}
