package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import com.avaje.ebean.annotation.PrivateOwned;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views.CategoryView;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Marcin on 20.
 */
@javax.persistence.Entity
@Table(name = "CATEGORY")
public class Category extends Entity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @PrivateOwned
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_ID")
    private List<BreakDown> typicalBreakDowns;

    public Category(String name, List<String> typicalBreakDowns) {
        this.name = name;
        this.typicalBreakDowns = breakDownsFromStringList(typicalBreakDowns);
    }

    private ImmutableList<BreakDown> breakDownsFromStringList(List<String> typicalBreakDowns) {
        return FluentIterable.from(typicalBreakDowns)
                .transform(new Function<String, BreakDown>() {
                    @Nullable
                    @Override
                    public BreakDown apply(@Nullable String bds) {
                        return new BreakDown(bds);
                    }
                })
                .toList();
    }


    public String getName() {
        return name;
    }

    public ImmutableList<String> getTypicalBreakDowns() {
        return FluentIterable.from(typicalBreakDowns).transform(new Function<BreakDown, String>() {
            @Nullable
            @Override
            public String apply(@Nullable BreakDown bd) {
                return bd.toString();
            }
        }).toList();
    }

    public CategoryView toView() {
        CategoryView categoryView = new CategoryView();
        categoryView.setId(getId());
        categoryView.setName(name);
        categoryView.setTypicalBreakDowns(getTypicalBreakDowns());
        return categoryView;
    }

    public static Category fromView(CategoryView categoryView){
        return new Category(categoryView.getName(), categoryView.getTypicalBreakDowns());
    }

    public void update(CategoryView categoryView){
        name = categoryView.getName();
        typicalBreakDowns.clear();;
        typicalBreakDowns.addAll(breakDownsFromStringList(categoryView.getTypicalBreakDowns()));
    }
}
