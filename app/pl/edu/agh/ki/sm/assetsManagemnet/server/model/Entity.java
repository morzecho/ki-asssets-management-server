package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Marcin on 20.
 */
@MappedSuperclass
public abstract class Entity extends Model {

    @Id
    private Long id;

    protected Long getId(){
        return id;
    }
}
