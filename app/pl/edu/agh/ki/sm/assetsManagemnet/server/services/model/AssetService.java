package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.AssetDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Asset;

/**
 * Created by Marcin on 21.
 */
@Service
public class AssetService extends EntityService<Asset, AssetDAO> {

    @Autowired
    private AssetDAO assetDAO;

    @Override
    protected AssetDAO entityDAO() {
        return assetDAO;
    }
}
