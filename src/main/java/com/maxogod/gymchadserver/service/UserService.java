package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.repository.UserRepository;
import org.springframework.stereotype.Service;

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
