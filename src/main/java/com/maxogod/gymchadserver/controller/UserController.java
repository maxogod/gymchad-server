package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; // Autowired (dependency injection)
    }

    @PostMapping("/login") // TODO - Sessions
    public ResponseEntity<User> login(@RequestBody User user) {
        User foundUser = this.userService.login(user);
        if (foundUser == null) {
            User newUser = this.userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/logout") // TODO - Close the session
    public ResponseEntity<String> logout(HttpSession session) {
//        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/session") // TODO - Return the actual session
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getSession());
    }
}
