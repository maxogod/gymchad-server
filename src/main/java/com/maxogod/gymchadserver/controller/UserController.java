package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service; // Autowired (dependency injection)
    }

    @PostMapping("/auth/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return service.login(user);
    }

    @GetMapping("/auth/session")
    public ResponseEntity<List<User>> getAllUsers() {
        return this.service.getSession();
    }
}
