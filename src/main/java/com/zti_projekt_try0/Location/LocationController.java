package com.zti_projekt_try0.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getCurrentLocations(){
        return locationService.getCurrentLocations();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable("id") Long id) {
        return ResponseEntity.notFound().build();
//        Optional<Location> location = locationService.findById(id);
//        if (location.isPresent()) {
//            locationService.delete(location.get());
//            return ResponseEntity.ok("Location with ID " + id + " has been deleted.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

}
