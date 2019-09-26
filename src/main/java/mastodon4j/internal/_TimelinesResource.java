package mastodon4j.internal;

import mastodon4j.Range;
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
            Range range) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/home")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    builder.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    builder.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    builder.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    builder.query("min_id", range.getMinId().get());
                }
            }

            return builder.get();
        });
    }

    @Override
    public Response<Status[]> getPublicTimeline(
            Boolean local,
            Boolean onlyMedia,
            Range range) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/public")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);


            addParam(builder, "local", local);
            addParam(builder, "only_media", onlyMedia);

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    builder.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    builder.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    builder.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    builder.query("min_id", range.getMinId().get());
                }
            }

            return builder.get();
        });
    }

    @Override
    public Response<Status[]> getHashtagTimeline(
            String hashtag,
            Boolean local,
            Boolean onlyMedia,
            Range range) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/tag/{hashtag}")
                            .pathValue("hashtag", _InternalUtility.encode(hashtag))
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

            addParam(builder, "local", local);
            addParam(builder, "only_media", onlyMedia);

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    builder.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    builder.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    builder.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    builder.query("min_id", range.getMinId().get());
                }
            }

            return builder.get();
        });
    }

    @Override
    public Response<Status[]> getListTimeline(
            String listId,
            Range range) {

        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/timelines/list/{listId}")
                            .pathValue("listId", listId)
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

            if (range != null) {
                if (range.getLimit().isPresent()) {
                    builder.query("limit", range.getLimit().get());
                }
                if (range.getSinceId().isPresent()) {
                    builder.query("since_id", range.getSinceId().get());
                }
                if (range.getMaxId().isPresent()) {
                    builder.query("max_id", range.getMaxId().get());
                }
                if (range.getMinId().isPresent()) {
                    builder.query("min_id", range.getMinId().get());
                }
            }

            return builder.get();
        });
    }

}
