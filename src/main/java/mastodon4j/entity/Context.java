package mastodon4j.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hecateball
 */
public class Context implements Serializable {

    @SerializedName( "statuses")
    private Status[] statuses;
    @SerializedName( "descendants")
    private Status[] descendants;

    public Status[] getStatuses() {
        return statuses;
    }

    public void setStatuses(Status[] statuses) {
        this.statuses = statuses;
    }

    public Status[] getDescendants() {
        return descendants;
    }

    public void setDescendants(Status[] descendants) {
        this.descendants = descendants;
    }

}
