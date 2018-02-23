package mastodon4j;

import mastodon4j.api.*;

/**
 * @author hecateball
 */
public interface Mastodon extends AccountsResource, AppsResource, BlocksResource, FavouritesResource,
        FollowRequestsResource, FollowsResource, InstancesResource, MediaResource, MutesResource, NotificationsResource,
        OauthResource, ReportsResource, SearchResource, StatusesResource, StreamingResource, TimelinesResource {

    public AccountsResource accounts();

    public AppsResource apps();

    public BlocksResource blocks();

    public FavouritesResource favourites();

    public FollowRequestsResource followRequests();

    public FollowsResource follows();

    public InstancesResource instances();

    public MediaResource media();

    public MutesResource mutes();

    public NotificationsResource notifications();

    public OauthResource oauth();

    public ReportsResource reports();

    public SearchResource search();

    public StatusesResource statuses();

    public StreamingResource streaming();

    public TimelinesResource timelines();
}
