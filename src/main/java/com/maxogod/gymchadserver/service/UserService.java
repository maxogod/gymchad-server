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

    public User login(User user) {
        Optional<User> foundUser = this.repository.findByEmail(user.getEmail());
        return foundUser.orElse(null);
    }

    public User createUser(User user) {
        if (user.getName().isEmpty() || user.getEmail().isEmpty()) return null;
        return this.repository.save(user);
    }

    public List<User> getSession() {
        return repository.findAll();
    }

}
