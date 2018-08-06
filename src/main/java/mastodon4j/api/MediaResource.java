package mastodon4j.api;

import mastodon4j.entity.Attachment;
import mastodon4j.entity.share.Response;

/**
 *
 * @author hecateball
 */
public interface MediaResource {

    /**
     * Uploading a media attachment.
     *
     * @param file media to be uploaded
     * @return an Attachment that can be used when creating a status
     */
    public Response<Attachment> postMedia(String file);
}
