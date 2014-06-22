package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Category;

/**
 * Created by Marcin on 20.
 */
@Repository
public class CategoryDAO extends EntityDAO<Category> {

    public CategoryDAO() {
        super(Category.class);
    }
}
