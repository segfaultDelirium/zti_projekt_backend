package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.other.ModificationResult;
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


    public ModificationResult deactivateLocation(int location_id){
        return locationRepository.deactivateLocation(location_id);
    }

    public ModificationResult updateLocation(Location location){
        return this.locationRepository.updateLocation(location);
    }
}
