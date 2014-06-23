package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.androidApi;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueStatus;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.IssueService;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 20.
 */
@org.springframework.stereotype.Controller
public class IssuesController extends BaseController {

    @Autowired
    private IssueService issueService;

    @BodyParser.Of(BodyParser.Json.class)
    public Result createIssue(){
        authenticate();

        IssueDTO issueDTO = fromJson(IssueDTO.class);
        issueService.createIssue(issueDTO, getHeader("token"));

        return ok();
    }

    public Result issuesByUser(){
        authenticate();

        List<IssueDTO> issues = issueService.issuesForUserWithToken(getHeader("token"));

        return ok(Json.toJson(issues));
    }
}
