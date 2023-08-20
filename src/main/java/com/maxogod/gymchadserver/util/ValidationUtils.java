package com.maxogod.gymchadserver.util;

import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.model.User;

public class ValidationUtils {

    public Boolean isExerciseValid(Exercise exercise) {
        return !exercise.getName().isEmpty();
    }

    public Boolean isUserValid(User user) {
        return !(user.getEmail().isEmpty() || user.getGoogleId().isEmpty());
    }

}
