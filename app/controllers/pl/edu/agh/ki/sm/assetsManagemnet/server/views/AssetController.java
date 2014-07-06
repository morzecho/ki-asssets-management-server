package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.views;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.Category;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.AssetService;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.CategoryService;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import views.html.asset;
import views.html.assetsBrowse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Marcin on 1.
 */
@Controller
@Security.Authenticated(UserAuthenticator.class)
public class AssetController extends BaseController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private CategoryService categoryService;

    public Result newAsset() {
        return ok(asset.render(Form.form(AssetView.class), availableCategories()));
    }

    private Map<String, String> availableCategories(){
        return categoryService
                .all()
                .stream()
                .map(Category::toView)
                .collect(Collectors.toMap(category -> category.getId().toString(), CategoryView::getName));
    }

    public Result all(){
        List<AssetView> assetsViews = assetService.all().stream().map(asset -> asset.toView()).collect(Collectors.toList());
        return ok(assetsBrowse.render(assetsViews));
    }

    public Result createAsset(){
        Form<AssetView> assetForm = Form.form(AssetView.class).bindFromRequest();
        assetService.createAsset(assetForm.get());
        return all();
    }

    public Result delete(Long id){
        assetService.delete(id);
        return all();
    }

}
