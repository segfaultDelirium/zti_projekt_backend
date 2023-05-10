package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;
import com.zti_projekt_try0.other.ModificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    List<Location> getCurrentLocations(){
        String query = "SELECT * FROM zti_projekt2.get_current_locations()";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            int locationId = rs.getInt("location_id");
            boolean isActive = rs.getBoolean("is_active");
            String streetAddress = rs.getString("street_address");
            String city = rs.getString("city");
            String zipcode = rs.getString("zipcode");
            String state = rs.getString("state");
            String countryCode = rs.getString("country_code");
            int countryCodeId = rs.getInt("country_code_id");
            boolean countryCodeIsActive = rs.getBoolean("is_country_code_active");
            String activityName = rs.getString("activity_name");
            int activityId = rs.getInt("activity_id");
            boolean activityIsActive = rs.getBoolean("is_activity_active");
            String companyName = rs.getString("company_name");
            return new Location(locationId, isActive, streetAddress, city, zipcode, state, companyName,
                    new CountryCode(countryCodeId, countryCodeIsActive, countryCode),
                    new Activity(activityId, activityIsActive, activityName));
        });
    }

    void addLocation(){

    }

    private static final class ModificationResultMapper implements RowMapper<ModificationResult> {
        public ModificationResult mapRow(ResultSet rs, int rowNum) throws SQLException {
            boolean success = rs.getBoolean("success");
            String message = rs.getString("message");
            return new ModificationResult(success, message);
        }
    }

    ModificationResult deactivateLocation(int location_id){
        String sql = "SELECT * from zti_projekt2.deactivate_location(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{location_id}, new ModificationResultMapper());
    }

    public ModificationResult updateLocation(Location location) {

        String sql = "SELECT * from zti_projekt2.update_location(?, ?, ?, ?, ?, ?, ?, ?)";

        // Define the parameters for the function
        Object[] params = new Object[] { location.getLocationId(), location.getStreetAddress(), location.getCity(), location.getZipcode(), location.getState(),
                location.getCountryCodeIdOrNull(), location.getActivityIdOrNull(), location.getCompanyName() };

        // Execute the function and get the result
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            boolean success = rs.getBoolean("success");
            String message = rs.getString("message");
            return new ModificationResult(success, message);
        });

    }

}
