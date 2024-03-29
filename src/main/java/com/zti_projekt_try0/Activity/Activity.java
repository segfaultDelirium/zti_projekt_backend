package com.zti_projekt_try0.Activity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ActivityDeserializer.class)
public class Activity {

    private Integer activityId;
    private boolean isActive;
    private String activityName;

    public Activity(int activityId, boolean isActive, String activityName) {
        this.activityId = activityId;
        this.isActive = isActive;
        this.activityName = activityName;
    }

    public Activity(int activityId, String activityName) {
        this.activityId = activityId;
        this.activityName = activityName;
    }



    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Activity(){

    }


}
