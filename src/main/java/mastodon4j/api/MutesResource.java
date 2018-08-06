package mastodon4j.api;

import mastodon4j.entity.Account;
import mastodon4j.entity.share.Response;

/**
 *
 * @author hecateball
 */
public interface MutesResource {

    /**
     * Fetching a user's mutes.
     *
     * @return an array of Accounts muted by the authenticated user
     */
    public Response<Account[]> getMutes();
}
