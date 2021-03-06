package mastodon4j.internal;

import mastodon4j.Range;
import mastodon4j.api.NotificationsResource;
import mastodon4j.entity.Alert;
import mastodon4j.entity.Notification;
import mastodon4j.entity.Subscription;
import mastodon4j.entity.share.Response;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import java.util.List;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _NotificationsResource implements NotificationsResource {

    private final String uri;
    private final String bearerToken;

    _NotificationsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Response<Notification[]> getNotifications(
            Range range,
            List<String> excludeTypes,
            Long id) {

        return proceed(Notification[].class, () -> {

            HttpRequestBuilder builder =
                    new HttpRequestBuilder()
                            .target(this.uri)
                            .path("/api/v1/notifications")
                            .request(HttpMediaType.APPLICATION_JSON)
                            .header("Authorization", this.bearerToken);

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
                if (range.getMinId().isPresent()) {
                    builder.query("min_id", range.getMinId().get());
                }
            }

            if (excludeTypes != null) {
                for (String excludeType : excludeTypes) {
                    builder.query("exclude_types[]", excludeType);
                }
            }

            if (id != null) {
                builder.query("account_id", id);
            }

            return builder.get();
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

    @Override
    public Response<Subscription> getSubscription() {

        return proceed(Subscription.class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/push/subscription");

            return builder.request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Response<Subscription> pushSubscription(
            String endpoint,
            String p256dh,
            String auth,
            Alert alert) {

        return proceed(Subscription.class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/push/subscription");

            builder.param("subscription[endpoint]", endpoint);
            builder.param("subscription[keys][p256dh]", p256dh);
            builder.param("subscription[keys][auth]", auth);

            if (alert != null) {
                if (alert.getFollow() != null) {
                    builder.param("data[alerts][follow]", booleanValue(alert.getFollow()));
                }
                if (alert.getFavourite() != null) {
                    builder.param("data[alerts][favourite]", booleanValue(alert.getFavourite()));
                }
                if (alert.getReblog() != null) {
                    builder.param("data[alerts][reblog]", booleanValue(alert.getReblog()));
                }
                if (alert.getMention() != null) {
                    builder.param("data[alerts][mention]", booleanValue(alert.getMention()));
                }
                if (alert.getPoll() != null) {
                    builder.param("data[alerts][poll]", booleanValue(alert.getPoll()));
                }
                if (alert.getStatus() != null) {
                    builder.param("data[alerts][status]", booleanValue(alert.getStatus()));
                }
            }

            return builder.request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }

    @Override
    public Response<Subscription> editSubscription(Alert alert) {
        return proceed(Subscription.class, () -> {

            HttpRequestBuilder builder = new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/push/subscription");

            if (alert != null) {
                if (alert.getFollow() != null) {
                    builder.param("data[alerts][follow]", booleanValue(alert.getFollow()));
                }
                if (alert.getFavourite() != null) {
                    builder.param("data[alerts][favourite]", booleanValue(alert.getFavourite()));
                }
                if (alert.getReblog() != null) {
                    builder.param("data[alerts][reblog]", booleanValue(alert.getReblog()));
                }
                if (alert.getMention() != null) {
                    builder.param("data[alerts][mention]", booleanValue(alert.getMention()));
                }
                if (alert.getPoll() != null) {
                    builder.param("data[alerts][poll]", booleanValue(alert.getPoll()));
                }
                if (alert.getStatus() != null) {
                    builder.param("data[alerts][status]", booleanValue(alert.getStatus()));
                }
            }

            return builder.request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .put();
        });
    }

    private String booleanValue(Boolean b) {
        return b ? "true" : "false";
    }
}
