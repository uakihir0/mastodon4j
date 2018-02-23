package mastodon4j.internal;

import mastodon4j.Range;
import mastodon4j.api.BlocksResource;
import mastodon4j.entity.Account;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _BlocksResource implements BlocksResource {

    private final String uri;
    private final String bearerToken;

    _BlocksResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Account[] getBlocks() {
        return this.getBlocks(null);
    }

    @Override
    public Account[] getBlocks(Range range) {
        return proceed(Account[].class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/blocks");

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
