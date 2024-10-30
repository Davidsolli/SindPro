package com.pidw.sindPro.controllers.auth;

import com.pidw.sindPro.dtos.users.UserDTO;
import com.pidw.sindPro.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> findMe() {
        UserDTO user = userService.findMe();
        return ResponseEntity.ok(user);
    }
}
