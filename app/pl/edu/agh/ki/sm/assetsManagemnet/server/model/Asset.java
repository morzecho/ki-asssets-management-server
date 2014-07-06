package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views.AssetView;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetWithTypicalBreakDownsDTO;

import javax.persistence.*;

/**
 * Created by Marcin on 20.
 */
@javax.persistence.Entity
@Table(name = "ASSET")
public class Asset extends Entity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    public Asset(String name, Location location, Category category) {
        this.name = name;
        this.location = location;
        this.category = category;
    }

    public AssetDTO toAssetDTO(){
        return new AssetDTO(getId(), name, category.getName(), location.getLocationName());
    }

    public AssetWithTypicalBreakDownsDTO toAssetWithTypicalBreakDownsDTO(){
        return new AssetWithTypicalBreakDownsDTO(toAssetDTO(), category.getTypicalBreakDowns());
    }

    public AssetView toView(){
        AssetView assetView = new AssetView();
        assetView.setId(getId());
        assetView.setName(name);
        assetView.setLocation(location.getLocationName());
        assetView.setCategory(category.toView());
        return assetView;
    }


}
