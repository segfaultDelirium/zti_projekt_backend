package com.zti_projekt_try0.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getCurrentActivities(){
        return this.activityRepository.getCurrentActivities();
    }
}
