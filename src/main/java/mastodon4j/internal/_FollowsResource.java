package mastodon4j.internal;

import mastodon4j.api.FollowsResource;
import mastodon4j.entity.Account;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _FollowsResource implements FollowsResource {

    private final String uri;
    private final String bearerToken;

    _FollowsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Account follow(String uri) {
        return proceed(Account.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/follow_requests")
                    .param("uri", uri)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

}
