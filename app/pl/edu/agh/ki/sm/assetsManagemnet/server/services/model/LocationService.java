package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.LocationDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Location;

/**
 * Created by Marcin on 21.
 */
@Service
public class LocationService extends EntityService<Location, LocationDAO>{

    @Autowired
    private LocationDAO locationDAO;

    @Override
    protected LocationDAO entityDAO() {
        return locationDAO;
    }

    public Location getByName(String locationName) {
        return locationDAO.getByName(locationName);
    }
}
