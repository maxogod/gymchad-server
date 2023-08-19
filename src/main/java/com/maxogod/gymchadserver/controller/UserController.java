package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service; // Autowired (dependency injection)
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User foundUser = this.service.login(user);
        if (foundUser == null) {
            User newUser = this.service.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
//        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/session")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.service.getSession());
    }
}
