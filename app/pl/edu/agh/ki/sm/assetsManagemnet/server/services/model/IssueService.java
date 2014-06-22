package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.acl.IssueTrackerACL;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;

import java.util.List;

/**
 * Created by Marcin on 21.
 */
@Service
public class IssueService {

    @Autowired
    private IssueTrackerACL issueTracker;

    @Autowired
    private UserService userService;

    public void createIssue(IssueDTO issueDTO) {
        issueTracker.createIssue(issueDTO);
    }

    public List<IssueDTO> issuesForUserWithToken(String token) {
        User user = userService.getByToken(token);
        return issueTracker.issuesForUser(user);
    }
}
