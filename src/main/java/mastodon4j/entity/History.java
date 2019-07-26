package mastodon4j.entity;

import java.io.Serializable;

/**
 * @author uakihir0
 */
public class History implements Serializable {

    private String day;

    private Long uses;

    private Long accounts;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getUses() {
        return uses;
    }

    public void setUses(Long uses) {
        this.uses = uses;
    }

    public Long getAccounts() {
        return accounts;
    }

    public void setAccounts(Long accounts) {
        this.accounts = accounts;
    }
}
