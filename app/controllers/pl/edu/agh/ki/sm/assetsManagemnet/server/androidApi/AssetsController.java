package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.androidApi;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetWithTypicalBreakDownsDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.AssetService;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Result;


/**
 * Created by Marcin on 20.
 */
@Controller
@Transactional
public class AssetsController extends BaseController {

    @Autowired
    private AssetService assetService;

    public AssetsController() {
        super();
    }

    public Result assetById(Long id){
        authenticateByToken();

        AssetWithTypicalBreakDownsDTO assetWithTypicalBreakDowns = assetService.getByIdOrThrowException(id).toAssetWithTypicalBreakDownsDTO();
        return ok(Json.toJson(assetWithTypicalBreakDowns));
    }
}
