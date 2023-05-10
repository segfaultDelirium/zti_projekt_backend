package com.zti_projekt_try0.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ActivityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Activity> getCurrentActivities() {
        String sql = "SELECT * FROM zti_projekt2.get_current_activities()";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int countryCodeId = rs.getInt("activity_id");
            boolean isActive = rs.getBoolean("is_active");
            String activityName = rs.getString("activity_name");
            return new Activity(countryCodeId, isActive, activityName);
        });
    }

}
