package mastodon4j.internal;

import mastodon4j.api.TimelinesResource;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.addParam;
import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _TimelinesResource implements TimelinesResource {

    private final String uri;
    private final String bearerToken;

    _TimelinesResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Response<Status[]> getHomeTimeline(
            Long maxId,
            Long sinceId,
            Long minId,
            Long limit) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/home")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

            addParam(builder, "max_id", maxId);
            addParam(builder, "since_id", sinceId);
            addParam(builder, "min_id", minId);
            addParam(builder, "limit", limit);

            return builder.get();
        });
    }

    @Override
    public Response<Status[]> getPublicTimeline(
            Boolean local,
            Boolean onlyMedia,
            Long maxId,
            Long sinceId,
            Long minId,
            Long limit) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/public")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);


            addParam(builder, "local", local);
            addParam(builder, "only_media", onlyMedia);
            addParam(builder, "max_id", maxId);
            addParam(builder, "since_id", sinceId);
            addParam(builder, "min_id", minId);
            addParam(builder, "limit", limit);

            return builder.get();
        });
    }

    @Override
    public Response<Status[]> getHashtagTimeline(
            String hashtag,
            Boolean local,
            Boolean onlyMedia,
            Long maxId,
            Long sinceId,
            Long minId,
            Long limit) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/tag/{hashtag}")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

            addParam(builder, "local", local);
            addParam(builder, "only_media", onlyMedia);
            addParam(builder, "max_id", maxId);
            addParam(builder, "since_id", sinceId);
            addParam(builder, "min_id", minId);
            addParam(builder, "limit", limit);

            return builder.get();
        });
    }

}
