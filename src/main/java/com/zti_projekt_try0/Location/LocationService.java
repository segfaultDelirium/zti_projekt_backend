package com.zti_projekt_try0.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getCurrentLocations(){
        return locationRepository.getCurrentLocations();
    }


    public void deactivateLocation(){

    }
}
