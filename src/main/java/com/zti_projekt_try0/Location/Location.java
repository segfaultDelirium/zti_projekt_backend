package com.zti_projekt_try0.Location;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;

import java.sql.Timestamp;

@JsonDeserialize(using = LocationDeserializer.class)
public class Location {

    private Integer locationId;
    private Boolean isActive;

    private String streetAddress;
    private String city;
    private String zipcode;
    private String state;

    private String companyName;

    private CountryCode countryCode;

    private Activity activity;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    private Timestamp timestamp;

    public Location(Integer locationId, Boolean isActive, String streetAddress, String city, String zipcode, String state, String companyName, CountryCode countryCode, Activity activity, Timestamp timestamp) {
        this.locationId = locationId;
        this.isActive = isActive;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.companyName = companyName;
        this.countryCode = countryCode;
        this.activity = activity;
        this.timestamp = timestamp;
    }

    public Location(){}

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
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


    Integer getActivityIdOrNull(){
        if(this.getActivity() == null) return null;
        return this.getActivity().getActivityId();
    }

    Integer getCountryCodeIdOrNull(){
        if(this.getCountryCode() == null) return null;
        return this.getCountryCode().getCountryCodeId();
    }





}
