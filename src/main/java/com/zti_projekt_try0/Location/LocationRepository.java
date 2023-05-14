package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;
import com.zti_projekt_try0.other.CreationResult;
import com.zti_projekt_try0.other.ModificationResult;
import com.zti_projekt_try0.other.ModificationResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
                    new Activity(activityId, activityIsActive, activityName), null);
        });
    }

    CreationResult createLocation(Location location){
        String sql = "SELECT * from zti_projekt2.create_location(?, ?, ?, ?, ?, ?, ?, ?)";

        // Define the parameters for the function
        Object[] params = new Object[] { location.isActive(), location.getStreetAddress(), location.getCity(), location.getZipcode(), location.getState(),
                location.getCountryCodeIdOrNull(), location.getActivityIdOrNull(), location.getCompanyName() };

        // Execute the function and get the result
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            boolean success = rs.getBoolean("success");
            String message = rs.getString("message");
            Integer createdRecordId = rs.getInt("created_record_id");
            return new CreationResult(success, message, createdRecordId);
        });
    }



    ModificationResult deactivateLocation(int location_id){
        String sql = "SELECT * from zti_projekt2.deactivate_location(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{location_id}, new ModificationResultMapper());
    }


    ModificationResult reactivateLocation(int location_id){
        String sql = "SELECT * from zti_projekt2.reactivate_location(?)";
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

    public List<Location> getLocationTimelineGroupedByTimestamp(Integer parameter) {
        String sql = "select * from zti_projekt2.get_location_timeline_grouped_by_timestamp(?)";
        Object[] args = new Object[]{parameter};
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
            Location location = new Location();
            location.setActive(rs.getBoolean("is_active"));
            location.setStreetAddress(rs.getString("street_address"));
            location.setCity(rs.getString("city"));
            location.setZipcode(rs.getString("zipcode"));
            location.setState(rs.getString("state"));
            location.setCompanyName(rs.getString("company_name"));
            CountryCode countryCode = new CountryCode();
            countryCode.setCountryCodeId(rs.getInt("country_code_id"));
            countryCode.setCountryCode(rs.getString("country_code_code"));
            location.setCountryCode(countryCode);
            Activity activity = new Activity();
            activity.setActivityId(rs.getInt("activity_id"));
            activity.setActivityName(rs.getString("activity_name"));
            location.setActivity(activity);
            location.setTimestamp(rs.getTimestamp("timestamp"));
            return location;
        });
    }

    public List<Location> getLocationsAtGivenTime(Timestamp timestamp) {
        String sql = "SELECT * FROM zti_projekt2.get_locations_at_given_timestamp_wrapper(?)";
        Object[] args = {timestamp};
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
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
                    new Activity(activityId, activityIsActive, activityName), null);
        });
    }

    public Location getCurrentLocation(int id){
        String query = "select * from zti_projekt2.get_current_locations()\n" +
                "where location_id = ?";
        Object[] args = {id};
        List<Location> oneOrZeroResults = jdbcTemplate.query(query, args, (rs, rowNum) -> {
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
                    new Activity(activityId, activityIsActive, activityName), null);
        });
        return oneOrZeroResults.get(0);
    }

    public List<Location> getLocationsWhichChangedBetweenTimestamps(Timestamp startDate, Timestamp endDate){
        String query = "select * from zti_projekt2.get_locations_which_changed_between_timestamps(?, ?)";
        Object[] args = {startDate, endDate};
        return jdbcTemplate.query(query, args, (rs, rowNum) -> {
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
                    new Activity(activityId, activityIsActive, activityName), null);
        });
    }


}
