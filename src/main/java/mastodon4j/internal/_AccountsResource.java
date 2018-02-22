package mastodon4j.internal;

import com.google.gson.Gson;
import mastodon4j.MastodonException;
import mastodon4j.Range;
import mastodon4j.api.AccountsResource;
import mastodon4j.entity.Account;
import mastodon4j.entity.Error;
import mastodon4j.entity.Relationship;
import mastodon4j.entity.Status;
import net.socialhub.http.*;

/**
 * @author hecateball
 */
final class _AccountsResource implements AccountsResource {

    private static final long DEFAULT_LIMIT = 40;
    private final String uri;
    private final String bearerToken;
    private final Gson gson;


    _AccountsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
        this.gson = _InternalUtility.getGsonInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account verifyCredentials() {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/verify_credentials")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }
        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account updateCredentials(String displayName, String note, String avatar, String header) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/update_credentials")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)

                    .param("display_name", displayName)
                    .param("note", note)
                    .param("avatar", avatar)
                    .param("header", header)
                    .patch();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }
        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account getAccount(long id) {

        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account[] getFollowers(long id) {
        return this.getFollowers(id, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account[] getFollowers(long id, Range range) {
        try {
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
            }

            HttpResponse response = build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account[].class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account[] getFollowing(long id) {
        return this.getFollowing(id, null);
    }

    @Override
    public Account[] getFollowing(long id, Range range) {
        try {
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
            }
            HttpResponse response = build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account[].class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getStatuses(long id) {
        return this.getStatuses(id, false, false, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getStatuses(long id, Range range) {
        return this.getStatuses(id, false, false, range);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getStatuses(long id, boolean onlyMedia, boolean excluedeReplies, Range range) {
        try {
            HttpRequestBuilder build = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/statuses")
                    .pathValue("id", String.valueOf(id));

            if (onlyMedia) {
                build.query("only_media", onlyMedia);
            }
            if (excluedeReplies) {
                build.query("exclude_replies", excluedeReplies);
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
            }

            HttpResponse response = build
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Status[].class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship follow(long id) {

        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/follow")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship unfollow(long id) {

        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unfollow")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship block(long id) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/block")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship unblock(long id) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unblock")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship mute(long id) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/mute")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship unmute(long id) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/{id}/unmute")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship.class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship[] relationships(long id, long... ids) {
        try {
            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/relationships")
                    .param("id[]", id);

            if (ids != null) {
                for (long i : ids) {
                    builder.param("id[]", i);
                }
            }

            HttpResponse response = builder
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Relationship[].class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    @Override
    public Account[] search(String query) {
        return this.search(query, DEFAULT_LIMIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account[] search(String query, long limit) {
        try {
            HttpResponse response = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/accounts/search")
                    .param("q", query)
                    .param("limit", limit)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();

            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), Account[].class);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }

        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }
}
