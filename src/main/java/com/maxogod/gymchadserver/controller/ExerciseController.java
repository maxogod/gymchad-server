package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.service.ActivityService;
import com.maxogod.gymchadserver.service.ExerciseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ActivityService activityService;

    public ExerciseController(ExerciseService exerciseService, ActivityService activityService) {
        this.exerciseService = exerciseService;
        this.activityService = activityService;
    }

    @DeleteMapping("/{exerciseId}") // TODO - Authenticate
    public ResponseEntity<String> deleteExercise(@PathVariable String exerciseId) {
        this.activityService.deleteExerciseFromActivity(exerciseId);
        this.exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok("Deleted exercise");
    }

    @PutMapping("/{exerciseId}") // TODO - Authenticate
    public ResponseEntity<Exercise> updateExercise(@PathVariable String exerciseId, @RequestBody Exercise exercise) {
        Exercise updatedExercise =  this.exerciseService.updateExercise(exerciseId, exercise);
        if (updatedExercise == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(updatedExercise);
    }

    @GetMapping("/athus")
    public ResponseEntity<Integer> count(HttpSession session) {

        Integer counter = (Integer) session.getAttribute("count");

        if (counter == null) {
            counter = 1;
        } else {
            counter++;
        }

        session.setAttribute("count", counter);

        return ResponseEntity.ok(counter);
    }

}
