package com.maxogod.gymchadserver.dtos;

import com.maxogod.gymchadserver.model.Activity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private String name;
    private String email;
    private String picture;
    private List<Activity> activities;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String picture, List<Activity> activities) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.activities = activities;
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
