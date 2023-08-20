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

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ActivityService activityService;

    private final UserService userService;

    public ExerciseController(ExerciseService exerciseService, ActivityService activityService, UserService userService) {
        this.exerciseService = exerciseService;
        this.activityService = activityService;
        this.userService = userService;
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable String exerciseId, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivityByExercise(exerciseId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        this.activityService.deleteExerciseFromActivity(exerciseId);
        this.exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok("Deleted exercise");
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable String exerciseId, @RequestBody Exercise exercise, HttpSession session) {
        User user = this.userService.getUserFromSession(session);
        if (user == null) return ResponseEntity.badRequest().body(null);
        Activity activity = this.activityService.getActivityByExercise(exerciseId);
        if (activity == null) return ResponseEntity.notFound().build();
        if (!this.userService.isActivityOfUser(user, activity)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        Exercise updatedExercise =  this.exerciseService.updateExercise(exerciseId, exercise);
        if (updatedExercise == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(updatedExercise);
    }

}
