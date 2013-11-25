package com.logicify.htb.configurator.htb.filters;

/**
 * <h1>Mark</h1>
 * <p>This class keeps parameter that makes up "fw" filter rules that select traffic for
 * each of the classes according to firewall "mark".
 * It contains fields: <ul>
 * <li><b>firewallRule</b>-keeps firewall "mark"</li>
 * <li><b>comment</b>-keeps comment to this "mark"</li>
 * </ul> </p>
 */
public class Mark {
    private String firewallRule;
    private String comment;

    public Mark(String firewallRule, String comment) {
        this.firewallRule = firewallRule;
        this.comment = comment;
    }

    public String getFirewallRule() {
        return firewallRule;
    }

    public void setFirewallRule(String firewallRule) {
        this.firewallRule = firewallRule;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (firewallRule != null ? !firewallRule.equals(mark.firewallRule) : mark.firewallRule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firewallRule != null ? firewallRule.hashCode() : 0;
        return result;
    }

}
