package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.dtos.UserDTO;
import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.repository.UserRepository;
import com.maxogod.gymchadserver.util.ValidationUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final ValidationUtils validationUtils;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, ValidationUtils validationUtils) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.validationUtils = validationUtils;
    }

    public UserDTO login(User user, HttpSession session) {
        if (!validationUtils.isUserValid(user)) return null;

        UserDTO sessionUser = this.getSession(session);
        if (sessionUser != null) return sessionUser;

        Optional<User> foundUser = this.repository.findByEmail(user.getEmail());
        if (foundUser.isEmpty()) return null;
        if (!this.passwordEncoder.matches(user.getGoogleId(), foundUser.get().getGoogleId())) return null;

        session.setAttribute("user", foundUser.get());
        return new UserDTO(foundUser.get().getName(), foundUser.get().getEmail(), foundUser.get().getPicture(), foundUser.get().getActivities());
    }

    public UserDTO createUser(User user, HttpSession session) {
        if (!validationUtils.isUserValid(user)) return null;

        Optional<User> existingUser = this.repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) return null;

        user.setGoogleId(this.passwordEncoder.encode(user.getGoogleId()));
        User newUser = this.repository.save(user);

        session.setAttribute("user", newUser);
        return new UserDTO(newUser.getName(), newUser.getEmail(), newUser.getPicture(), newUser.getActivities());
    }

    public UserDTO getSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return null;
        Optional<User> optionalUser = this.repository.findById(user.getId());
        if (optionalUser.isEmpty()) return null;
        return new UserDTO(optionalUser.get().getName(), optionalUser.get().getEmail(), optionalUser.get().getPicture(), optionalUser.get().getActivities());
    }

    public void deleteActivityFromUser(String activityId) {
        Optional<User> optionalUser = this.repository.findUserByActivityId(activityId);
        if (optionalUser.isEmpty()) return;
        User user = optionalUser.get();
        List<Activity> activities = user.getActivities();
        activities.removeIf(activity -> activity.getId().equals(activityId));
        user.setActivities(activities);
        this.repository.save(user);
    }

}
