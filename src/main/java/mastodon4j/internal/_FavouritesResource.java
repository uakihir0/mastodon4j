package mastodon4j.internal;

import mastodon4j.Range;
import mastodon4j.api.FavouritesResource;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _FavouritesResource implements FavouritesResource {

    private final String uri;
    private final String bearerToken;

    _FavouritesResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Response<Status[]> getFavourites() {
        return this.getFavourites(null);
    }

    @Override
    public Response<Status[]> getFavourites(Range range) {
        return proceed(Status[].class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/favourites");

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
            }

            return builder
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }
}
