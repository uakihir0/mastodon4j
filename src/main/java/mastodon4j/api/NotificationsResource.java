package mastodon4j.api;

import mastodon4j.Range;
import mastodon4j.entity.Notification;
import mastodon4j.entity.share.Response;

import java.util.List;

/**
 * @author hecateball
 */
public interface NotificationsResource {

    /**
     * Fetching a user's notifications.
     *
     * @return a list of Notifications for the authenticated user
     */
    public Response<Notification[]> getNotifications(
            Range range,
            List<String> excludeTypes,
            Long id);

    /**
     * Getting a single notification.
     *
     * @param id
     * @return the Notification
     */
    public Response<Notification> getNotification(long id);

    /**
     * Clearing notifications. Deletes all notifications from the Mastodon server for the authenticated user.
     */
    public Response<Void> clearNotifications();

}
