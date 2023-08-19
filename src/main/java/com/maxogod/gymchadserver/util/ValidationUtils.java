package com.maxogod.gymchadserver.util;

import com.maxogod.gymchadserver.model.Exercise;

public class ValidationUtils {

    public Boolean isExerciseValid(Exercise exercise) {
        return !exercise.getName().isEmpty();
    }

}
