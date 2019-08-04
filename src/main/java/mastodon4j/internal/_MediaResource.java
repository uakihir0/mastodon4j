package mastodon4j.internal;

import mastodon4j.api.MediaResource;
import mastodon4j.entity.Attachment;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import java.io.File;
import java.io.InputStream;

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
    public Response<Attachment> postMedia(InputStream stream, String name) {
        return proceed(Attachment.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/media")
                    .file("media", stream, name)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Attachment> postMedia(File file) {
        return proceed(Attachment.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/media")
                    .file("media", file)
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }
}
