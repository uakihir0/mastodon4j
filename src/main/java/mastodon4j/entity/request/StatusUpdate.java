package mastodon4j.entity.request;

import java.util.List;

public class StatusUpdate {

    private Long inReplyToId;

    private Boolean isSensitive;

    private String spoilerText;

    private String visibility;

    private String status;

    private String content;

    private List<Long> mediaIds;

    private List<String> pollOptions;

    private Long pollExpiresIn;

    private Boolean pollMultiple;

    private Boolean pollHideTotals;

    public Long getInReplyToId() {
        return inReplyToId;
    }

    public void setInReplyToId(Long inReplyToId) {
        this.inReplyToId = inReplyToId;
    }

    public Boolean getSensitive() {
        return isSensitive;
    }

    public void setSensitive(Boolean sensitive) {
        isSensitive = sensitive;
    }

    public String getSpoilerText() {
        return spoilerText;
    }

    public void setSpoilerText(String spoilerText) {
        this.spoilerText = spoilerText;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<Long> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public List<String> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(List<String> pollOptions) {
        this.pollOptions = pollOptions;
    }

    public Long getPollExpiresIn() {
        return pollExpiresIn;
    }

    public void setPollExpiresIn(Long pollExpiresIn) {
        this.pollExpiresIn = pollExpiresIn;
    }

    public Boolean getPollMultiple() {
        return pollMultiple;
    }

    public void setPollMultiple(Boolean pollMultiple) {
        this.pollMultiple = pollMultiple;
    }

    public Boolean getPollHideTotals() {
        return pollHideTotals;
    }

    public void setPollHideTotals(Boolean pollHideTotals) {
        this.pollHideTotals = pollHideTotals;
    }
}
