package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.other.DeactivateRecordResult;
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
    public ResponseEntity<DeactivateRecordResult> deleteLocation(@PathVariable("id") int id) {
        DeactivateRecordResult result = this.locationService.deactivateLocation(id);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }



}
