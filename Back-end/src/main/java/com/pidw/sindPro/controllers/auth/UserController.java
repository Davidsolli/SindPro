package com.pidw.sindPro.controllers.auth;

import com.pidw.sindPro.dtos.users.UserRegisterDTO;
import com.pidw.sindPro.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public ResponseEntity<UserRegisterDTO> findMe() {
        UserRegisterDTO user = userService.findMe();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserRegisterDTO>> findAll() {
        List<UserRegisterDTO> result = userService.findAll();
        return ResponseEntity.ok(result);
    }
}
