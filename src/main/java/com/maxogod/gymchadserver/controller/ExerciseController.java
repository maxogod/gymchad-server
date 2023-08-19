package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable String exerciseId) {
        this.service.deleteExercise(exerciseId);
        return ResponseEntity.ok("Deleted exercise");
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable String exerciseId, @RequestBody Exercise exercise) {
        Exercise updatedExercise =  this.service.updateExercise(exerciseId, exercise);
        if (updatedExercise == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(updatedExercise);
    }

}
