package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.dtos.UserDTO;
import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; // Autowired (dependency injection)
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user, HttpSession session) {
        UserDTO foundUser = this.userService.login(user, session);
        if (foundUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user, HttpSession session) {
        UserDTO newUser = this.userService.createUser(user, session);
        if (newUser == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/session")
    public ResponseEntity<UserDTO> getSession(HttpSession session) {
        UserDTO user = this.userService.getSession(session);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(user);
    }

}

// Toy example to test sessions
//    @GetMapping("/athus")
//    public ResponseEntity<User> count(HttpSession session) {
//
//        User counter = (User) session.getAttribute("user");
//
//        if (counter == null) {
//            counter = new User();
//        } else {
//            counter = null;
//        }
//
//        session.setAttribute("user", counter);
//
//        return ResponseEntity.ok(counter);
//    }
