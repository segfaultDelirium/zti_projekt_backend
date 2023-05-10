package com.zti_projekt_try0.Location;


import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;

public class Location {

    private int locationId;
    private boolean isActive;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private String streetAddress;
    private String city;
    private String zipcode;
    private String state;

    private String companyName;

    private CountryCode countryCode;

    private Activity activity;

    public Location(int locationId, boolean isActive, String streetAddress, String city, String zipcode, String state, String companyName, CountryCode countryCode, Activity activity) {
        this.locationId = locationId;
        this.isActive = isActive;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.companyName = companyName;
        this.countryCode = countryCode;
        this.activity = activity;
    }





}