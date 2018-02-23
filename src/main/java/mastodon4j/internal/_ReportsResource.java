package mastodon4j.internal;

import mastodon4j.api.ReportsResource;
import mastodon4j.entity.Report;
import net.socialhub.http.HttpMediaType;
import net.socialhub.http.HttpRequestBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

import static mastodon4j.internal._InternalUtility.proceed;

/**
 * @author hecateball
 */
final class _ReportsResource implements ReportsResource {

    private final String uri;
    private final String bearerToken;

    _ReportsResource(String uri, String accessToken) {
        this.uri = uri;
        this.bearerToken = _InternalUtility.getBearerToken(accessToken);
    }

    @Override
    public Report[] getReports() {
        return proceed(Report[].class, () -> {

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/reports")
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .get();
        });
    }

    @Override
    public Report postReport(long accountId, long[] statusIds, String comment) {
        //TODO: NOT WORKING?

        return proceed(Report.class, () -> {

            String statuses =
                    Arrays.stream(statusIds)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(","));

            return new HttpRequestBuilder()
                    .target(this.uri)
                    .path("/api/v1/reports")
                    .param("comment", comment)
                    .param("status_ids", statuses)
                    .param("account_id", String.valueOf(accountId))
                    .request(HttpMediaType.APPLICATION_JSON)
                    .header("Authorization", this.bearerToken)
                    .post();
        });
    }
}
