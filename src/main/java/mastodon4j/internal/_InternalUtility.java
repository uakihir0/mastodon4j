package mastodon4j.internal;

import com.google.gson.Gson;
import mastodon4j.MastodonException;
import mastodon4j.entity.Error;
import net.socialhub.http.HttpException;
import net.socialhub.http.HttpResponse;
import net.socialhub.http.HttpResponseCode;

/**
 * @author hecateball
 */
class _InternalUtility {

    private final static Gson gson = new Gson();

    private _InternalUtility() {
    }

    static String getBearerToken(String accessToken) {
        return "Bearer " + accessToken;
    }

    static Gson getGsonInstance() {
        return gson;
    }

    static void proceed(RequestInterface function) {
        try {
            HttpResponse response = function.proceed();
            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return;
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }
        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    static <T> T proceed(Class<T> clazz, RequestInterface function) {
        try {
            HttpResponse response = function.proceed();
            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    return gson.fromJson(response.asString(), clazz);
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }
        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    interface RequestInterface<T> {
        HttpResponse proceed() throws HttpException;
    }
}
