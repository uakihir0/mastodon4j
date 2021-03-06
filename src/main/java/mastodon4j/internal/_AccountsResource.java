package mastodon4j.internal;

import mastodon4j.Range;
import mastodon4j.api.AccountsResource;
import mastodon4j.entity.Account;
import mastodon4j.entity.Relationship;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _AccountsResource implements AccountsResource {

    private static final long DEFAULT_LIMIT = 40;
    private final String uri;
    private final String bearerToken;


    _AccountsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account> verifyCredentials() {
        return proceed(Account.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/verify_credentials")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account> updateCredentials(String displayName, String note, String avatar, String header) {
        return proceed(Account.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/update_credentials")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)

                    .param("display_name", displayName)
                    .param("note", note)
                    .param("avatar", avatar)
                    .param("header", header)
                    .patch();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account> getAccount(long id) {
        return proceed(Account.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account[]> getFollowers(long id) {
        return this.getFollowers(id, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account[]> getFollowers(long id, Range range) {
        return proceed(Account[].class, () -> {

            HttpRequestBuilder build = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/followers")
                    .pathValue("id", String.valueOf(id));

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    build.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    build.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    build.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    build.query("min_id", range.getMinId().get());
                }
            }

            return build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account[]> getFollowing(long id) {
        return this.getFollowing(id, null);
    }

    @Override
    public Response<Account[]> getFollowing(long id, Range range) {
        return proceed(Account[].class, () -> {

            HttpRequestBuilder build = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/following")
                    .pathValue("id", String.valueOf(id));

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    build.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    build.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    build.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    build.query("min_id", range.getMinId().get());
                }
            }

            return build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Status[]> getStatuses(long id) {
        return this.getStatuses(id, false, false, false, false, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Status[]> getStatuses(long id, Range range) {
        return this.getStatuses(id, false, false, false, false, range);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Status[]> getStatuses(
            long id,
            boolean onlyPinned,
            boolean onlyMedia,
            boolean excluedeReplies,
            boolean excludeReblogs,
            Range range) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder build =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/accounts/{id}/statuses")
                            .pathValue("id", String.valueOf(id));

            if (onlyMedia) {
                build.query("only_media", onlyMedia);
            }
            if (onlyPinned) {
                build.query("pinned", onlyPinned);
            }
            if (excluedeReplies) {
                build.query("exclude_replies", excluedeReplies);
            }
            if (excludeReblogs) {
                build.query("exclude_reblogs", excludeReblogs);
            }
            if (range != null) {
                if (range.getLimit().isPresent()) {
                    build.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    build.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    build.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    build.query("min_id", range.getMinId().get());
                }
            }

            return build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> follow(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/follow")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> unfollow(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unfollow")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> block(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/block")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> unblock(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unblock")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> mute(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/mute")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship> unmute(long id) {
        return proceed(Relationship.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unmute")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Relationship[]> relationships(long id, long... ids) {
        return proceed(Relationship[].class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/relationships")
                    .param("id[]", id);

            if (ids != null) {
                for (long i : ids) {
                    builder.param("id[]", i);
                }
            }

            return builder
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Account[]> search(String query) {
        return this.search(query, DEFAULT_LIMIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<Account[]> search(String query, long limit) {
        return proceed(Account[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/search")
                    .param("q", query)
                    .param("limit", limit)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }
}
