package org.andrey_bayev.htb_configurator.htb.filters;

/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/3/13
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mark
{
    private String firewallRule;
    private String comment;

    public Mark(String firewallRule, String comment)
    {
        this.firewallRule = firewallRule;
        this.comment = comment;
    }

    public String getFirewallRule()
    {
        return firewallRule;
    }

    public void setFirewallRule(String firewallRule)
    {
        this.firewallRule = firewallRule;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (comment != null ? !comment.equals(mark.comment) : mark.comment != null) return false;
        if (firewallRule != null ? !firewallRule.equals(mark.firewallRule) : mark.firewallRule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firewallRule != null ? firewallRule.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

}
