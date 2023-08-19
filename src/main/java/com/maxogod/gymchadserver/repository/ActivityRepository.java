package com.maxogod.gymchadserver.repository;

import com.maxogod.gymchadserver.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {



}
