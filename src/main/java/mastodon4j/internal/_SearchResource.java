package mastodon4j.internal;

import mastodon4j.api.SearchResource;
import mastodon4j.entity.Results;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _SearchResource implements SearchResource {

    private final String uri;

    _SearchResource(String uri) {
        this.uri = uri;
    }

    @Override
    public Response<Results> search(String query, boolean resolve) {
        return proceed(Results.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/search")
                    .query("q", query)
                    .query("resolve", resolve)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .get();
        });
    }

}
