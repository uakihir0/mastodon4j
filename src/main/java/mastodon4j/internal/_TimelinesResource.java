package mastodon4j.internal;

import mastodon4j.api.TimelinesResource;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

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
    public Response<Status[]> getHomeTimeline() {
        //TODO: need to support:local, max_id, since_id, limit

        return proceed(Status[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/timelines/home")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Status[]> getPublicTimeline(boolean local) {
        //TODO: need to support:local, max_id, since_id, limit

        return proceed(Status[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/timelines/public")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Status[]> getHashtagTimeline(String hashtag, boolean local) {
        //TODO: need to support:local, max_id, since_id, limit

        return proceed(Status[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/timelines/tag/{hashtag}")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

}
