package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Marcin on 20.
 */
@javax.persistence.Entity
@Table(name = "LOCATION")
public class Location extends Entity {

    @Column(name = "LOCATION_NAME", unique = true, nullable = false)
    private String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
