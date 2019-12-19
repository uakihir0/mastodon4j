package mastodon4j.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author hecateball
 */
public class Status implements Serializable {

    @SerializedName("id")
    private long id;
    @SerializedName("uri")
    private String uri;
    @SerializedName("url")
    private String url;
    @SerializedName("account")
    private Account account;
    @SerializedName("in_reply_to_id")
    private Long inReplyToId;
    @SerializedName("in_reply_to_account_id")
    private Long inReplyToAccountId;
    @SerializedName("reblog")
    private Status reblog;
    @SerializedName("content")
    private String content;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("reblogs_count")
    private long reblogsCount;
    @SerializedName("favourites_count")
    private long favouritesCount;
    @SerializedName("reblogged")
    private boolean reblogged;
    @SerializedName("favourited")
    private boolean favourited;
    @SerializedName("sensitive")
    private boolean sensitive;
    @SerializedName("spoiler_text")
    private String spoilerText;
    @SerializedName("visibility")
    private String visibility;
    @SerializedName("media_attachments")
    private Attachment[] mediaAttachments;
    @SerializedName("mentions")
    private Mention[] mentions;
    @SerializedName("tags")
    private Tag[] tags;
    @SerializedName("emojis")
    private Emoji[] emojis;
    @SerializedName("application")
    private Application application;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getInReplyToId() {
        return inReplyToId;
    }

    public void setInReplyToId(Long inReplyToId) {
        this.inReplyToId = inReplyToId;
    }

    public Long getInReplyToAccountId() {
        return inReplyToAccountId;
    }

    public void setInReplyToAccountId(Long inReplyToAccountId) {
        this.inReplyToAccountId = inReplyToAccountId;
    }

    public Status getReblog() {
        return reblog;
    }

    public void setReblog(Status reblog) {
        this.reblog = reblog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getReblogsCount() {
        return reblogsCount;
    }

    public void setReblogsCount(long reblogsCount) {
        this.reblogsCount = reblogsCount;
    }

    public long getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public boolean isReblogged() {
        return reblogged;
    }

    public void setReblogged(boolean reblogged) {
        this.reblogged = reblogged;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public void setSensitive(boolean sensitive) {
        this.sensitive = sensitive;
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

    public Attachment[] getMediaAttachments() {
        return mediaAttachments;
    }

    public void setMediaAttachments(Attachment[] mediaAttachments) {
        this.mediaAttachments = mediaAttachments;
    }

    public Mention[] getMentions() {
        return mentions;
    }

    public void setMentions(Mention[] mentions) {
        this.mentions = mentions;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Emoji[] getEmojis() {
        return emojis;
    }

    public void setEmojis(Emoji[] emojis) {
        this.emojis = emojis;
    }
}
