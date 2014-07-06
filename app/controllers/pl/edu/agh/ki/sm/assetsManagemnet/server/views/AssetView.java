package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views;

import play.data.validation.Constraints;

/**
 * Created by Marcin on 5.
 */
public class AssetView {

    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String location;

    @Constraints.Required
    private CategoryView category;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CategoryView getCategory() {
        return category;
    }

    public void setCategory(CategoryView category) {
        this.category = category;
    }

    private CategoryView getCategoryOrCreateNew(){
        if(category == null){
            category = new CategoryView();
        }
        return category;
    }

    public String getCategoryId() {
        return getCategoryOrCreateNew().getId().toString();
    }

    public void setCategoryId(String categoryId) {
        getCategoryOrCreateNew().setId(Long.parseLong(categoryId));
    }
}
