package mastodon4j.internal;

import mastodon4j.api.StatusesResource;
import mastodon4j.entity.Account;
import mastodon4j.entity.Card;
import mastodon4j.entity.Context;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _StatusesResource implements StatusesResource {

    private final String uri;
    private final String bearerToken;

    _StatusesResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Response<Status> getStatus(long id) {
        return proceed(Status.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

    @Override
    public Response<Context> getContext(long id) {
        return proceed(Context.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/context")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

    @Override
    public Response<Card> getCard(long id) {
        return proceed(Card.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/card")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

    @Override
    public Response<Account[]> getRebloggedBy(long id) {
        //TODO: need to support: max_id, since_id, limit

        return proceed(Account[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/reblogged_by")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

    @Override
    public Response<Account[]> getFavouritedBy(long id) {
        //TODO: need to support: max_id, since_id, limit

        return proceed(Account[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/favourited_by")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

    @Override
    public Response<Status> postStatus(Status status) {
        return proceed(Status.class, () -> {

            //TODO: support media_ids
            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses");

            builder.param("status", status.getContent());
            if (status.getInReplyToId() != null) {
                builder.param("in_reply_to_id", String.valueOf(status.getInReplyToId()));
            }
            if (status.isSensitive()) {
                builder.param("sensitive", "true");
            }
            if (status.getSpoilerText() != null && !status.getSpoilerText().isEmpty()) {
                builder.param("spoiler_text", status.getSpoilerText());
            }
            if (status.getVisibility() != null && !status.getVisibility().isEmpty()) {
                builder.param("visibility", status.getVisibility());
            }

            return builder.request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Void> deleteStatus(long id) {
        return proceed(() -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .delete();
        });
    }

    @Override
    public Response<Status> reblog(long id) {
        return proceed(Status.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/reblog")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Status> unreblog(long id) {
        return proceed(Status.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/unreblog")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Status> favourite(long id) {
        return proceed(Status.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/favourite")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Status> unfavourite(long id) {
        return proceed(Status.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/statuses/{id}/unfavourite")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }
}
