package mastodon4j.internal;

import mastodon4j.api.MediaResource;
import mastodon4j.entity.Attachment;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _MediaResource implements MediaResource {

    private final String uri;
    private final String bearerToken;

    _MediaResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Response<Attachment> postMedia(String file) {
        return proceed(Attachment.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/favourites")
                    .param("media", file)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

}
