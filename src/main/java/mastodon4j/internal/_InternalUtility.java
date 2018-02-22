package mastodon4j.internal;

import com.google.gson.Gson;

/**
 *
 * @author hecateball
 */
class _InternalUtility {

    private final static Gson gson = new Gson();

    private _InternalUtility() {
    }

    static String getBearerToken(String accessToken) {
        return "Bearer " + accessToken;
    }

    static Gson getGsonInstance(){
        return gson;
    }
}
