package pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos;

/**
 * Created by Marcin on 20.
 */
public class AssetDTO {
    private Long id;
    private String name;
    private String category;
    private String location;

    /**
     * for jackson only
     */
    public AssetDTO() {
    }

    public AssetDTO(Long id, String name, String category, String location) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
