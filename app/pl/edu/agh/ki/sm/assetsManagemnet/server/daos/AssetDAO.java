package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Asset;

/**
 * Created by Marcin on 20.
 */
@Repository
public class AssetDAO extends EntityDAO<Asset> {

    public AssetDAO() {
        super(Asset.class);
    }
}