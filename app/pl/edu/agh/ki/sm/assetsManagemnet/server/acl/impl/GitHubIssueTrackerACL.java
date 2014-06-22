package pl.edu.agh.ki.sm.assetsManagemnet.server.acl.impl;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.acl.IssueTrackerACL;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;

import java.util.List;

/**
 * Created by Marcin on 21.
 */
@Service
public class GitHubIssueTrackerACL implements IssueTrackerACL {
    private static final String GITHUB_TOKEN = "cebdc8a3c438f125fccd3276cf51797e88786b69";

    @Override
    public void createIssue(IssueDTO issueDTO) {

    }

    @Override
    public List<IssueDTO> issuesForUser(User user) {
        return null;
    }
}
