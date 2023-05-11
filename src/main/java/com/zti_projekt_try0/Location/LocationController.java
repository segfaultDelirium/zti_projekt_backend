package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.other.CreationResult;
import com.zti_projekt_try0.other.ModificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ModificationResult> deleteLocation(@PathVariable("id") int id) {
        ModificationResult result = this.locationService.deactivateLocation(id);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModificationResult> reactivateLocation(@PathVariable("id") int id) {

        ModificationResult result = this.locationService.reactivateLocation(id);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }
    @PutMapping
    public ModificationResult updateLocation(@RequestBody Location location) {
        // your code to update the location based on the JSON data in the request body goes here
        return this.locationService.updateLocation(location);
    }

    @PostMapping
    public CreationResult createLocation(@RequestBody Location location){
        return this.locationService.createLocation(location);
    }

    @GetMapping("/{id}")
    public List<Location> getLocationTimelineGroupedByTimestamp(@PathVariable("id") int id){
        return this.locationService.getLocationTimelineGroupedByTimestamp(id);
    }

}
