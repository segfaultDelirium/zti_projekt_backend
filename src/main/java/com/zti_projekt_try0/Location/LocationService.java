package com.zti_projekt_try0.Location;

import com.zti_projekt_try0.other.CreationResult;
import com.zti_projekt_try0.other.ModificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getCurrentLocations(){
        return locationRepository.getCurrentLocations();
    }


    public ModificationResult deactivateLocation(int locationId){
        return locationRepository.deactivateLocation(locationId);
    }

    public ModificationResult updateLocation(Location location){
        return this.locationRepository.updateLocation(location);
    }

    public ModificationResult reactivateLocation(int locationId){
        return locationRepository.reactivateLocation(locationId);
    }

    public CreationResult createLocation(Location location){
        return this.locationRepository.createLocation(location);
    }

    public CompletableFuture<List<Location>> getLocationTimelineGroupedByTimestampAsync(int id) {
        return CompletableFuture.supplyAsync(() -> this.locationRepository.getLocationTimelineGroupedByTimestamp(id));
    }

    public CompletableFuture<Location> getCurrentLocationAsync(int id) {
        return CompletableFuture.supplyAsync(() -> this.locationRepository.getCurrentLocation(id));
    }

    public CompletableFuture<List<Location>> getTimelineWithCurrentLocationAsync(int id) {
        CompletableFuture<List<Location>> timelineFuture = getLocationTimelineGroupedByTimestampAsync(id);
        CompletableFuture<Location> currentLocationFuture = getCurrentLocationAsync(id);

        return timelineFuture.thenCombine(currentLocationFuture, (timeline, currentLocation) -> {
            timeline.add(0, currentLocation);
            return timeline;
        });
    }

    public List<Location> getLocationsAtGivenTime(Timestamp timestamp){

        return this.locationRepository.getLocationsAtGivenTime(timestamp);
    }

    public Location getCurrentLocation(int id){
        return this.locationRepository.getCurrentLocation(id);
    }
}
