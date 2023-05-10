package com.zti_projekt_try0.CountryCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryCodeService {

    @Autowired
    private CountryCodeRepository countryCodeRepository;

    List<CountryCode> getCurrentCountryCodes(){
        return this.countryCodeRepository.getCurrentCountryCodes();
    }

    public void addCountryCode(boolean isActive, String code){
        this.countryCodeRepository.addCountryCode(isActive, code);
    }
}
