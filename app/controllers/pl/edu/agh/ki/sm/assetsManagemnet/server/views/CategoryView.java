package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views;

import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin on 5.
 */
public class CategoryView {

    private Long id;

    @Constraints.Required
    private String name;

    private List<String> typicalBreakDowns;

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

    public List<String> getTypicalBreakDowns() {
        return typicalBreakDowns;
    }

    public void setTypicalBreakDowns(List<String> typicalBreakDowns) {
        this.typicalBreakDowns = typicalBreakDowns;
    }

    public String getTypicalBreakDownsAsString() {
        return typicalBreakDowns.stream().reduce((s1, s2) -> s1 + "\n" + s2).orElse("");
    }

    public void setTypicalBreakDownsAsString(String typicalBreakDowns) {
        if(notEmpty(typicalBreakDowns)) {
            this.typicalBreakDowns = Arrays.asList(typicalBreakDowns.split("\n"));
        } else {
            this.typicalBreakDowns = new ArrayList<String>();
        }
    }

    private boolean notEmpty(String str){
        return !(str == null || str.isEmpty());
    }
}
