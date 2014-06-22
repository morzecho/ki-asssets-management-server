package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetWithTypicalBreakDownsDTO;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Marcin on 20.
 */
@javax.persistence.Entity
@Table(name = "ASSET")
public class Asset extends Entity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID", nullable = false, updatable = false, insertable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false, insertable = false)
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
}
