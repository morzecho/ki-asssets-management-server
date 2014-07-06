package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.androidApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.AndroidUser;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.IssueService;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Marcin on 20.
 */
@Controller
public class IssuesController extends BaseController {

    @Autowired
    private IssueService issueService;

    public IssuesController() {
        super();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createIssue(){
        AndroidUser androidUser = authenticateByToken();

        IssueDTO issueDTO = fromJson(IssueDTO.class);
        issueService.createIssue(issueDTO, androidUser);

        return ok();
    }

    public Result issuesByUser(){
        AndroidUser androidUser = authenticateByToken();

        List<IssueDTO> issues = issueService.issuesForUserWithToken(androidUser);

        return ok(Json.toJson(issues));
    }
}
