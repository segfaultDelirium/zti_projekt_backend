package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.other.CreationResult;
import com.zti_projekt_try0.other.ModificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/at-given-time/{isoDateTime}")
    public List<Location> getLocationsAtGivenTime(@PathVariable("isoDateTime") String isoDateTime){

        Timestamp timestamp = Timestamp.valueOf(isoDateTime.replace("T", " ").replace("Z", ""));
        return this.locationService.getLocationsAtGivenTime(timestamp);
    }

    @GetMapping("/timeline/{id}")
    public CompletableFuture<List<Location>> getTimelineWithCurrentLocationAsync(@PathVariable int id) {
        return locationService.getTimelineWithCurrentLocationAsync(id);
    }

    @GetMapping("/{id}")
    public Location getCurrentLocation(@PathVariable("id") int id){
        return this.locationService.getCurrentLocation(id);
    }

    @GetMapping("/changes")
    public List<Location> getLocationsWhichChangedBetweenTimestamps(@RequestParam("startDate") String startDateString,
                                                                    @RequestParam("endDate") String endDateString){
        Timestamp startDate = LocationService.stringISODateTimeToTimestamp(startDateString);
        Timestamp endDate = LocationService.stringISODateTimeToTimestamp(endDateString);
        return this.locationService.getLocationsWhichChangedBetweenTimestamps(startDate, endDate);
    }
}
