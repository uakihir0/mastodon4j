package mastodon4j.streaming;

public interface LifeCycleListener {

    void onConnect();

    void onDisconnect();
}
