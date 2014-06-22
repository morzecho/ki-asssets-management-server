package pl.edu.agh.ki.sm.assetsManagemnet.server.model.androidDtos;

import java.util.Date;

/**
 * Created by Marcin on 20.
 */
public class IssueDTO {
    private Long id;
    private AssetDTO asset;
    private String breakDown;
    private IssueStatus issueStatus;
    private Date dateCreated;
    private Date lastModified;
    private String response;

    /**
     * for jackson only
     */
    public IssueDTO() {
    }

    public IssueDTO(Long id, AssetDTO asset, String breakDown, IssueStatus issueStatus, Date dateCreated, Date lastModified, String response) {
        this.id = id;
        this.asset = asset;
        this.breakDown = breakDown;
        this.issueStatus = issueStatus;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetDTO getAsset() {
        return asset;
    }

    public void setAsset(AssetDTO asset) {
        this.asset = asset;
    }

    public String getBreakDown() {
        return breakDown;
    }

    public void setBreakDown(String breakDown) {
        this.breakDown = breakDown;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
