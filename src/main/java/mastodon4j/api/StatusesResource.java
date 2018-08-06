package mastodon4j.api;

import mastodon4j.entity.Account;
import mastodon4j.entity.Card;
import mastodon4j.entity.Context;
import mastodon4j.entity.Status;
import mastodon4j.entity.share.Response;

/**
 *
 * @author hecateball
 */
public interface StatusesResource {

    /**
     * Fetching a status.
     *
     * @param id
     * @return a Status
     */
    public Response<Status> getStatus(long id);

    /**
     * Getting status context.
     *
     * @param id
     * @return a Context
     */
    public Response<Context> getContext(long id);

    /**
     * Getting a card associated with a status.
     *
     * @param id
     * @return a Card
     */
    public Response<Card> getCard(long id);

    /**
     * Getting who reblogged a status.
     *
     * @param id
     * @return an array of Accounts
     */
    public Response<Account[]> getRebloggedBy(long id);

    /**
     * Getting who favourited a status.
     *
     * @param id
     * @return an array of Accounts
     */
    public Response<Account[]> getFavouritedBy(long id);

    /**
     * Posting a new status.
     *
     * @param status
     * @return the new Status.
     */
    public Response<Status> postStatus(Status status);

    /**
     * Deleting a status.
     *
     * @param id
     */
    public Response<Void> deleteStatus(long id);

    /**
     * Reblogging a status.
     *
     * @param id
     */
    public Response<Status> reblog(long id);

    /**
     * Unreblogging a status.
     *
     * @param id
     */
    public Response<Status> unreblog(long id);

    /**
     * Favouriting a status.
     *
     * @param id
     * @return
     */
    public Response<Status> favourite(long id);

    /**
     * Unfavouriting a status.
     *
     * @param id
     * @return
     */
    public Response<Status> unfavourite(long id);
}
