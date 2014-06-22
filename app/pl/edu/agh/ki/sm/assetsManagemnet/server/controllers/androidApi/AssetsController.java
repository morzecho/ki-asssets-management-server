package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.androidApi;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetWithTypicalBreakDownsDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.AssetService;
import play.libs.Json;
import play.mvc.Result;


/**
 * Created by Marcin on 20.
 */
@org.springframework.stereotype.Controller
public class AssetsController extends BaseController {

    @Autowired
    private AssetService assetService;

    public Result assetById(Long id){
        authenticate();

        AssetWithTypicalBreakDownsDTO assetWithTypicalBreakDowns = assetService.getByIdOrThrowException(id).toAssetWithTypicalBreakDownsDTO();
        return ok(Json.toJson(assetWithTypicalBreakDowns));
    }
}
