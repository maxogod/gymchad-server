package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<User> login(User user) {
        Optional<User> foundUser = repository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            return ResponseEntity.ok(foundUser.get());
        }
        return ResponseEntity.status(201).body(this.repository.save(user));
    }

    public ResponseEntity<List<User>> getSession() {
        return ResponseEntity.ok(repository.findAll());
    }

}
