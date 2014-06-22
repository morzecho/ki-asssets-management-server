package pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos;

import java.util.List;

/**
 * Created by Marcin on 20.
 */
public class AssetWithTypicalBreakDownsDTO {
    private AssetDTO asset;
    private List<String> typicalBreakDowns;

    /**
     * for jackson only
     */
    public AssetWithTypicalBreakDownsDTO() {
    }

    public AssetWithTypicalBreakDownsDTO(AssetDTO asset, List<String> typicalBreakDowns) {
        this.asset = asset;
        this.typicalBreakDowns = typicalBreakDowns;
    }

    public AssetDTO getAsset() {
        return asset;
    }

    public void setAsset(AssetDTO asset) {
        this.asset = asset;
    }

    public List<String> getTypicalBreakDowns() {
        return typicalBreakDowns;
    }

    public void setTypicalBreakDowns(List<String> typicalBreakDowns) {
        this.typicalBreakDowns = typicalBreakDowns;
    }
}
