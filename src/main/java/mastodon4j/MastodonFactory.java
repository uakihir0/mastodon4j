package mastodon4j;

import mastodon4j.internal._Mastodon;

/**
 * @author hecateball
 */
public final class MastodonFactory {

    private static Mastodon SINGLETON;

    private MastodonFactory() {
    }

    public static Mastodon getInstance(String uri, String accessToken) {
        return new _Mastodon(uri, accessToken);
    }

    public static Mastodon getSingletonInstance(String uri, String accessToken) {
        if (MastodonFactory.SINGLETON == null) {
            MastodonFactory.SINGLETON = new _Mastodon(uri, accessToken);
        }
        return MastodonFactory.SINGLETON;
    }
}
