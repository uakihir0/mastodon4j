package mastodon4j.api;

import mastodon4j.entity.Account;
import mastodon4j.entity.share.Response;

/**
 *
 * @author hecateball
 */
public interface FollowRequestsResource {

    /**
     * Fetching a list of follow requests.
     *
     * @return an array of Accounts which have requested to follow the authenticated user
     */
    public Response<Account[]> getFollowRequests();

    /**
     * Authorizing follow requests.
     *
     * @param id
     */
    public Response<Void> authorizeFollowRequest(long id);

    /**
     * Rejecting follow requests.
     *
     * @param id
     */
    public Response<Void> rejectFollowRequest(long id);
}
