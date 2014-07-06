package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views.AssetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.AssetDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Asset;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Category;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Location;

/**
 * Created by Marcin on 21.
 */
@Service
public class AssetService extends EntityService<Asset, AssetDAO> {

    @Autowired
    private AssetDAO assetDAO;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LocationService locationService;

    @Override
    protected AssetDAO entityDAO() {
        return assetDAO;
    }

    public void createAsset(AssetView assetView) {
        Category category = categoryService.getById(assetView.getCategory().getId());
        Location location = getByNameOrCreateNew(assetView.getLocation());
        Asset asset = new Asset(assetView.getName(), location, category);
        assetDAO.save(asset);
    }

    private Location getByNameOrCreateNew(String locationName){
        Location location = locationService.getByName(locationName);
        if(location == null){
            location = new Location(locationName);
        }
        return location;
    }
}
