package mastodon4j.internal;

import com.google.gson.Gson;
import mastodon4j.MastodonException;
import mastodon4j.entity.Error;
import mastodon4j.entity.share.RateLimit;
import mastodon4j.entity.share.Response;
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

    static Response<Void> proceed(RequestInterface function) {
        try {
            HttpResponse response = function.proceed();
            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    Response<Void> result = new Response<>();
                    result.setRateLimit(RateLimit.of(response));
                    return result;
                default:
                    Error error = gson.fromJson(response.asString(), Error.class);
                    throw new MastodonException(error, response.getStatusCode());
            }
        } catch (HttpException e) {
            throw new MastodonException(e);
        }
    }

    static <T> Response<T> proceed(Class<T> clazz, RequestInterface function) {
        try {
            HttpResponse response = function.proceed();
            switch (response.getStatusCode()) {
                case HttpResponseCode.OK:
                    Response<T> result = new Response<>();
                    result.set(gson.fromJson(response.asString(), clazz));
                    result.setRateLimit(RateLimit.of(response));
                    return result;

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
