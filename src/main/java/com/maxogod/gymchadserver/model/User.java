package com.maxogod.gymchadserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("users")
public class User implements Serializable {

    @Id
    private String id;

//    @JsonIgnore OLD - I'm now using a dto bc this also ignores any googleId field in the request body
    private String googleId;

    private String name;

    private String email;

    private String picture;

    @DBRef
    private List<Activity> activities;

    public User() {
    }

    public User(String googleId ,String name, String email, String picture, List<Activity> activities) {
        this.googleId = googleId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.activities = activities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
