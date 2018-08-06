package mastodon4j.internal;

import mastodon4j.api.NotificationsResource;
import mastodon4j.entity.Notification;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _NotificationsResource implements NotificationsResource {

    private final String uri;
    private final String bearerToken;

    _NotificationsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = accessToken;
    }

    @Override
    public Response<Notification[]> getNotifications() {
        //TODO: need to support: max_id, since_id, limit

        return proceed(Notification[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/notifications")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Notification> getNotification(long id) {
        return proceed(Notification.class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/notifications/{id}")
                    .pathValue("id", String.valueOf(id))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Void> clearNotifications() {
        return proceed(() -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/notifications/clear")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }
}
