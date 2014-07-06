package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.CategoryService;
import play.data.Form;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;
import views.html.categoriesBrowse;
import views.html.category;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marcin on 1.
 */
@Controller
@Security.Authenticated(UserAuthenticator.class)
@Transactional
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    public Result all(){
        List<CategoryView> categoriesViews = categoryService.all().stream().map(category -> category.toView()).collect(Collectors.toList());
        return ok(categoriesBrowse.render(categoriesViews));
    }

    public Result newCategory(){
        return ok(category.render(Form.form(CategoryView.class)));
    }

    public Result createCategory(){
        Form<CategoryView> categoryForm = Form.form(CategoryView.class).bindFromRequest();
        categoryService.createCategory(categoryForm.get());
        return all();
    }

    public Result delete(Long id){
        categoryService.delete(id);
        return all();
    }

}
