package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import com.avaje.ebean.Ebean;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Entity;
import play.db.ebean.Model;

/**
 * Created by Marcin on 21.
 */
public abstract class EntityDAO<E extends Entity> {

    private Model.Finder<Long, E> finder;

    public EntityDAO(Class<E> entityClass) {
        finder = new Model.Finder<Long, E>(Long.class, entityClass);
    }

    protected Model.Finder<Long, E> find() {
        return finder;
    }

    public E getById(Long id){
        return finder.byId(id);
    }

    public void save(E entity){
        Ebean.save(entity);
    }
}
