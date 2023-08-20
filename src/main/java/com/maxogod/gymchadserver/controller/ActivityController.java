package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.service.ActivityService;
import com.maxogod.gymchadserver.service.ExerciseService;
import com.maxogod.gymchadserver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;
    private final UserService userService;

    public ActivityController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity newActivity = this.activityService.createActivity(activity);
        if (newActivity == null) return ResponseEntity.badRequest().body(null);
        this.userService.addActivityToUser(user, newActivity);
        return ResponseEntity.ok(newActivity);
    }

    @GetMapping("/")
    public ResponseEntity<List<Activity>> getActivities(HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(this.activityService.getActivitiesOfUser(user));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivity(@PathVariable String activityId, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivity(activityId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        return ResponseEntity.ok(activity);
    }

    @PostMapping("/add-exercise/{activityId}") // TODO - Authenticate
    public ResponseEntity<Activity> addExercise(@PathVariable String activityId, @RequestBody Exercise exercise, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivity(activityId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        Activity updatedActivity = this.activityService.addExercise(activityId, exercise);
        if (updatedActivity == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedActivity);
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String activityId, @RequestBody Activity newActivity, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivity(activityId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        Activity updatedActivity = this.activityService.updateActivity(activityId, newActivity);
        if (updatedActivity == null) return null;
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<String> deleteActivity(@PathVariable String activityId, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivity(activityId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        this.userService.deleteActivityFromUser(activityId);
        this.activityService.deleteActivity(activityId);
        return ResponseEntity.ok("Activity deleted successfully");
    }

}
