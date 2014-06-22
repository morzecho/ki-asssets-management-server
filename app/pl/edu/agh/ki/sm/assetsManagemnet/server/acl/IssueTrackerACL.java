package pl.edu.agh.ki.sm.assetsManagemnet.server.acl;

import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;

import java.util.List;

/**
 * Created by Marcin on 21.
 */
public interface IssueTrackerACL {

    void createIssue(IssueDTO issueDTO);

    List<IssueDTO> issuesForUser(User user);
}
