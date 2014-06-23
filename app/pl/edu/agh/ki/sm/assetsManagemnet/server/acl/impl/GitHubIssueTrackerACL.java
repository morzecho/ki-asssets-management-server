package pl.edu.agh.ki.sm.assetsManagemnet.server.acl.impl;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.acl.IssueTrackerACL;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.ExternalSystemException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.AssetDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueDTO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos.IssueStatus;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Marcin on 21.
 */
@Service
public class GitHubIssueTrackerACL implements IssueTrackerACL {

    private static final String GITHUB_TOKEN = "cebdc8a3c438f125fccd3276cf51797e88786b69";
    private static final String GITHUB_REPOSITORY = "kiagh/assetsManagement";
    public static final String CATEGORY_PREFIX = "category: ";
    public static final String LOCATION_PREFIX = "location: ";
    public static final String ISSUE_NAME_INFIX = " in ";

    @Autowired
    private UserService userService;

    @Override
    public void createIssue(IssueDTO issueDTO, User user) throws ExternalSystemException {
        try {
            GHRepository repository = getGhRepository();
            repository.createIssue(ghIssueName(issueDTO))
                    .body(issueDTO.getBreakDown())
                    .label(categoryToLabel(issueDTO))
                    .label(locationToLabel(issueDTO))
                    .milestone(getUserMilestonOrCreateIfNotExists(repository, user))
                    .create();
        } catch (IOException e) {
            throw new ExternalSystemException("Exception while creating issue via GitHub Api", e);
        }
    }

    private GHMilestone getUserMilestonOrCreateIfNotExists(GHRepository ghRepository, User user) throws IOException {
        GHMilestone milestone = getUserMilestone(ghRepository, user);
        if (milestone == null) {
            milestone = createMilestonForUser(ghRepository, user);
        }

        return milestone;
    }

    private GHMilestone createMilestonForUser(GHRepository ghRepository, User user) throws IOException {
        String milestoneTitle = "FakeMilestone " + UUID.randomUUID().toString();
        GHMilestone milestone = ghRepository.createMilestone(milestoneTitle, "Fake milestone which represents user");

        user.updateExternalSystemId(milestone.getNumber());
        userService.save(user);

        return milestone;
    }

    private GHMilestone getUserMilestone(GHRepository ghRepository, User user) throws IOException {
        Integer externalSystemId = user.getExternalSystemId();
        if(externalSystemId == null){
            return null;
        }

        return ghRepository.getMilestone(externalSystemId);
    }

    private GHRepository getGhRepository() throws IOException {
        GitHub gitHub = GitHub.connectUsingOAuth(GITHUB_TOKEN);
        return gitHub.getRepository(GITHUB_REPOSITORY);
    }

    private String ghIssueName(IssueDTO issue) {
        return issue.getAsset().getName() + ISSUE_NAME_INFIX + issue.getAsset().getLocation();
    }

    private String assetName(GHIssue ghIssue) {
        String[] splittedIssueName = ghIssue.getTitle().split(ISSUE_NAME_INFIX);
        if (splittedIssueName.length != 2) {
            return "Unknown assets";
        }
        return splittedIssueName[0];
    }

    private String categoryToLabel(IssueDTO issueDTO) {
        return CATEGORY_PREFIX + issueDTO.getAsset().getCategory();
    }

    private String categoryFromGhIssue(GHIssue ghIssue) {
        return fromLabel(ghIssue, CATEGORY_PREFIX, "Unknown category");
    }

    private String fromLabel(GHIssue ghIssue, String prefixToFind, String defaultVal){
        Optional<String> categoryLabel = ghIssue.getLabels()
                .stream()
                .map(ghLabel -> ghLabel.getName())
                .filter(label -> label.startsWith(prefixToFind))
                .map(label -> label.replaceFirst(prefixToFind, ""))
                .findFirst();

        return categoryLabel.orElse(defaultVal);
    }

    private String locationToLabel(IssueDTO issueDTO) {
        return LOCATION_PREFIX + issueDTO.getAsset().getLocation();
    }

    private String locationFromGhIssue(GHIssue ghIssue) {
        return fromLabel(ghIssue, LOCATION_PREFIX, "Unknown location");
    }

    @Override
    public List<IssueDTO> issuesForUser(User user) {
        try {
            GHRepository repository = getGhRepository();
            GHMilestone userMilestone = getUserMilestone(repository, user);

            if (userMilestone == null) {
                return Collections.emptyList();
            }

            Stream<GHIssue> openedIssues = repository.getIssues(GHIssueState.OPEN, userMilestone).stream();
            Stream<GHIssue> closedIssues = repository.getIssues(GHIssueState.CLOSED, userMilestone).stream();
            return Stream.concat(openedIssues, closedIssues)
                    .map(ghIssue -> convertToIssueDTO(ghIssue))
                    .filter(issue -> issue != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ExternalSystemException("Exception while retrieving issues from GitHub", e);
        }
    }

    private IssueDTO convertToIssueDTO(GHIssue ghIssue) {
        try {
            AssetDTO assetDTO = new AssetDTO(null, assetName(ghIssue), categoryFromGhIssue(ghIssue), locationFromGhIssue(ghIssue));
            return new IssueDTO(null, assetDTO, ghIssue.getBody(), resolveIssueStatus(ghIssue), ghIssue.getCreatedAt(),
                    ghIssue.getUpdatedAt(), getLastComment(ghIssue));
        } catch (Exception e) {
            //TODO jak zglaszac ze nie mozna zaladowac tylko niektorych ticketow?
            return null;
        }
    }

    private String getLastComment(GHIssue ghIssue) throws IOException {
        List<GHIssueComment> immutableComments = ghIssue.getComments();
        if (immutableComments.isEmpty()) {
            return "Without response";
        }

        List<GHIssueComment> comments = new ArrayList<>(immutableComments);
        comments.sort((comment1, comment2) -> comment2.getCreatedAt().compareTo(comment1.getCreatedAt()));
        return comments.get(0).getBody();
    }

    private IssueStatus resolveIssueStatus(GHIssue ghIssue) {
        GHIssueState state = ghIssue.getState();
        if (state.equals(GHIssueState.CLOSED)) {
            return IssueStatus.RESOLVED;
        }
        if (ghIssue.getAssignee() != null) {
            return IssueStatus.ASSIGNED;
        }
        return IssueStatus.NEW;
    }
}
