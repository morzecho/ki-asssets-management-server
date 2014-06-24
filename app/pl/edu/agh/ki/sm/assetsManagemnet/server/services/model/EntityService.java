package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.EntityDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.EntityNotFoundException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Entity;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;

/**
 * Created by Marcin on 21.
 */
public abstract class EntityService<E extends Entity, ED extends EntityDAO<E>> {

    protected abstract ED entityDAO();

    public E getById(Long id) {
        return entityDAO().getById(id);
    }

    public E getByIdOrThrowException(Long id) {
        E entity = getById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }

        return entity;
    }

    public void save(E entity){
        entityDAO().save(entity);
    }

}
