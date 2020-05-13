package mastodon4j.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author uakihir0
 */
public class Alert implements Serializable {

    @SerializedName("follow")
    private Boolean follow;
    @SerializedName("favourite")
    private Boolean favourite;
    @SerializedName("reblog")
    private Boolean reblog;
    @SerializedName("mention")
    private Boolean mention;
    @SerializedName("poll")
    private Boolean poll;

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Boolean getReblog() {
        return reblog;
    }

    public void setReblog(Boolean reblog) {
        this.reblog = reblog;
    }

    public Boolean getMention() {
        return mention;
    }

    public void setMention(Boolean mention) {
        this.mention = mention;
    }

    public Boolean getPoll() {
        return poll;
    }

    public void setPoll(Boolean poll) {
        this.poll = poll;
    }
}
