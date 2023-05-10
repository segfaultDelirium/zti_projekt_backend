package com.zti_projekt_try0.Location;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;



    List<Location> getCurrentLocations(){
        String query = "SELECT * FROM zti_projekt2.get_current_locations()";
        List<Location> locations = jdbcTemplate.query(query, (rs, rowNum) -> {
            int locationId = rs.getInt("location_id");
            boolean isActive = rs.getBoolean("is_active");
            String streetAddress = rs.getString("street_address");
            String city = rs.getString("city");
            String zipcode = rs.getString("zipcode");
            String state = rs.getString("state");
            String countryCode = rs.getString("country_code");
            String activityName = rs.getString("activity_name");
            String companyName = rs.getString("company_name");
            return new Location(locationId, isActive, streetAddress, city, zipcode, state, countryCode, activityName, companyName);
        });
        return locations;
    }

    void addLocation(){

    }
}
