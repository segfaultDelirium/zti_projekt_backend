package com.zti_projekt_try0.Location;



public class Location {
    public boolean getIsActive() {
        return is_active;
    }

    public String getStreetAddress() {
        return street_address;
    }

    public int getLocationId() {
        return location_id;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getState() {
        return state;
    }

    public String getCountryCode() {
        return country_code;
    }

    public String getActivityName() {
        return activity_name;
    }

    public String getCompanyName() {
        return company_name;
    }

    int location_id;
    boolean is_active;
    String street_address;
    String city;
    String zipcode;
    String state;
    String country_code;
    String activity_name;
    String company_name;

    public Location(int locationId, boolean isActive, String streetAddress, String city, String zipcode, String state, String countryCode, String activityName, String companyName) {
        this.location_id = locationId;
        this.is_active = isActive;
        this.street_address = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.country_code = countryCode;
        this.activity_name = activityName;
        this.company_name = companyName;
    }
}
