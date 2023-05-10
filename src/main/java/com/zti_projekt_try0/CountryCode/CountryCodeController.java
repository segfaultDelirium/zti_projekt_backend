package com.zti_projekt_try0.CountryCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/country_codes")
public class CountryCodeController {

    @Autowired
    private CountryCodeService countryCodeService;

    @GetMapping
    List<CountryCode> getCurrentCountryCodes(){
        return this.countryCodeService.getCurrentCountryCodes();
    }

    @PostMapping("/")
    public ResponseEntity<String> addCountryCode(@RequestBody CountryCode countryCodeRequest) {
        try {
            // Create a new country code with the given values
            countryCodeService.addCountryCode(
                    countryCodeRequest.getIsActive(),
                    countryCodeRequest.getCountryCode()
            );
            return ResponseEntity.ok("Country code added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding country code: " + e.getMessage());
        }
    }

}
