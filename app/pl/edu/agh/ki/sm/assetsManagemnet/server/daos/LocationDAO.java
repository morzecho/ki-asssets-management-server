package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Location;

/**
 * Created by Marcin on 20.
 */
@Repository
public class LocationDAO extends EntityDAO<Location> {

    public LocationDAO() {
        super(Location.class);
    }

    public Location getByName(String locationName) {
        return find().where().eq("LOCATION_NAME", locationName).findUnique();
    }
}
