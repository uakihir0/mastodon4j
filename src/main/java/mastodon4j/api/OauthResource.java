package mastodon4j.api;

import mastodon4j.entity.AccessToken;
import mastodon4j.entity.share.Response;

/**
 *
 * @author hecateball
 */
public interface OauthResource {

    /**
     * Attempt to login with the given credentials, and then retrieve the access token for the current user.
     *
     * @param clientId
     * @param clientSecret
     * @param emailAddress
     * @param password
     * @param scopes
     * @return an AccessToken
     */
    public Response<AccessToken> issueAccessToken(String clientId, String clientSecret, String emailAddress, String password, String scopes);

    /**
     * Attempt to login with authorization code, and then retrieve the access token for the current user.
     *
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     * @param code
     * @return an AccessToken
     */
    public Response<AccessToken> issueAccessToken(String clientId, String clientSecret, String redirectUri, String code);

    /**
     * Get Authorization URL
     *
     * @param clientId
     * @param redirectUri
     * @param scopes
     * @return an Authorization URL
     */
    public String getAuthorizationUrl(String clientId, String redirectUri, String scopes);
}
