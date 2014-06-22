package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import com.avaje.ebean.annotation.PrivateOwned;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

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
//        this.typicalBreakDowns = typicalBreakDowns
//                .stream()
//                .map(breakDown -> new BreakDown(breakDown))
//                .collect(Collectors.toList());
        //for stupid bug in eban class reader cannot use java8 stream !!!
        this.typicalBreakDowns = FluentIterable.from(typicalBreakDowns)
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

    public List<String> getTypicalBreakDowns() {
        return FluentIterable.from(typicalBreakDowns).transform(new Function<BreakDown, String>() {
            @Nullable
            @Override
            public String apply(@Nullable BreakDown bd) {
                return bd.toString();
            }
        }).toList();
    }
}
