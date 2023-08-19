package com.maxogod.gymchadserver.repository;

import com.maxogod.gymchadserver.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    @Query("{ 'exercises._id' : ?0 }")
    Optional<Activity> findActivityByExerciseId(String id);

}
