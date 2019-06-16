package mastodon4j.api;

import mastodon4j.Range;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;

/**
 * @author hecateball
 */
public interface TimelinesResource {

    /**
     * Retrieving a home timeline.
     *
     * @return an array of Statuses, most recent ones first
     */
    public Response<Status[]> getHomeTimeline(
            Range range);

    /**
     * Retrieving a public timeline.
     *
     * @param local (optional) only return statuses originating from this instance
     * @return an array of Statuses, most recent ones first
     */
    public Response<Status[]> getPublicTimeline(
            Boolean local,
            Boolean onlyMedia,
            Range range);

    /**
     * Retrieving a tag timeline.
     *
     * @param hashtag
     * @param local   (optional) only return statuses originating from this instance
     * @return an array of Statuses, most recent ones first
     */
    public Response<Status[]> getHashtagTimeline(
            String hashtag,
            Boolean local,
            Boolean onlyMedia,
            Range range);
}
