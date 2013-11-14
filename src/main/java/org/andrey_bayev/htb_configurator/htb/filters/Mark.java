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
    //todo: override equals and hashcode
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
}
