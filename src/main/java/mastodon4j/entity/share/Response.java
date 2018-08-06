package mastodon4j.entity.share;

/**
 * @author uakihir0
 */
public class Response<T> {

    private T object;

    private RateLimit limit;

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }

    public RateLimit getRateLimit() {
        return limit;
    }

    public void setRateLimit(RateLimit limit) {
        this.limit = limit;
    }
}
