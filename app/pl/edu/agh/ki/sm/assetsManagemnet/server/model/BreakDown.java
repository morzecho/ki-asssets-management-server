package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import javax.persistence.Table;

/**
 * Created as a hack for not implemented @CollectionTable in EBEAN
 * Created by Marcin on 21.
 */
@javax.persistence.Entity
@Table(name = "BREAK_DOWN")
public class BreakDown extends Entity {

    private String name;

    public BreakDown(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
