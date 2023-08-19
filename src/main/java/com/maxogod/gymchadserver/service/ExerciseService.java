package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.repository.ExerciseRepository;
import com.maxogod.gymchadserver.util.ValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    private final ValidationUtils validationUtils;

    public ExerciseService(ExerciseRepository repository, ValidationUtils validationUtils) {
        this.repository = repository;
        this.validationUtils = validationUtils;
    }

    public Exercise createExercise(Exercise exercise) {
        if (!validationUtils.isExerciseValid(exercise)) return null;
        return this.repository.save(exercise);
    }

    public void deleteExercise(String exerciseId) {
        this.repository.deleteById(exerciseId);
    }

    public Exercise updateExercise(String exerciseId, Exercise exercise) {
        if (!validationUtils.isExerciseValid(exercise)) return null;
        Exercise foundExercise = this.repository.findById(exerciseId).orElse(null);
        if (foundExercise == null) return null;
        foundExercise.setName(exercise.getName());
        foundExercise.setPicture(exercise.getPicture());
        foundExercise.setDescription(exercise.getDescription());
        foundExercise.setSets(exercise.getSets());
        foundExercise.setReps(exercise.getReps());
        foundExercise.setTime(exercise.getTime());
        return this.repository.save(foundExercise);
    }

}
