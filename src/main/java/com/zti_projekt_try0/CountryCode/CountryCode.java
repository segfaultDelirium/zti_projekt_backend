package com.zti_projekt_try0.CountryCode;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CountryCodeDeserializer.class)
public class CountryCode {

    private Integer countryCodeId;
    private boolean isActive;
    private String countryCode;
//    private Timestamp timestamp;

    public CountryCode(){}

    public CountryCode(int countryCodeId, boolean isActive, String countryCode
//            , Timestamp timestamp
    ) {
        this.countryCodeId = countryCodeId;
        this.isActive = isActive;
        this.countryCode = countryCode;
//        this.timestamp = timestamp;
    }

    public Integer getCountryCodeId() {
        return countryCodeId;
    }
    public void setCountryCodeId(Integer countryCodeId) {
        this.countryCodeId = countryCodeId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

//    public Timestamp getTimestamp() {
//        return timestamp;
//    }

}
