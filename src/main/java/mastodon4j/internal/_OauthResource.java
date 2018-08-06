package mastodon4j.internal;

import mastodon4j.api.OauthResource;
import mastodon4j.entity.AccessToken;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _OauthResource implements OauthResource {

    private final String uri;

    public _OauthResource(String uri) {
        this.uri = uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<AccessToken> issueAccessToken(String clientId, String clientSecret, String emailAddress, String password, String scopes) {
        return proceed(AccessToken.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/oauth/token")
                    .param("client_id", clientId)
                    .param("client_secret", clientSecret)
                    .param("username", emailAddress)
                    .param("password", password)
                    .param("scope", scopes)
                    .param("grant_type", "password")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .post();
        });
    }

}
