package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.acl.IssueTrackerACL;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.AndroidUser;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;

import java.util.List;

/**
 * Created by Marcin on 21.
 */
@Service
public class IssueService {

    @Autowired
    private IssueTrackerACL issueTracker;

    public void createIssue(IssueDTO issueDTO, AndroidUser androidUser) {
        issueTracker.createIssue(issueDTO, androidUser);
    }

    public List<IssueDTO> issuesForUserWithToken(AndroidUser androidUser) {
        return issueTracker.issuesForUser(androidUser);
    }
}
