package mastodon4j.internal;

import mastodon4j.api.MutesResource;
import mastodon4j.entity.Account;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _MutesResource implements MutesResource {

    private final String uri;
    private final String bearerToken;

    _MutesResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Account[] getMutes() {
        //TODO: need to support: max_id, since_id, limit

        return proceed(Account[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/mutes")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }
}
