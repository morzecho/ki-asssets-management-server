package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.CategoryDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Category;

/**
 * Created by Marcin on 21.
 */
@Service
public class CategoryService extends EntityService<Category, CategoryDAO>{

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    protected CategoryDAO entityDAO() {
        return categoryDAO;
    }
}
