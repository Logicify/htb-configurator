/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.filters;

/**
 * This class make up "u32" filter rules that select traffic for
 * #	each of the classes
 */
public class Rule
{
    private Address saddr;
    private Address daddr;
    private String comment;

    public Rule()
    {
        this.saddr = null;
        this.daddr = null;
        this.comment = null;
    }

    public Rule(String saddr, String daddr, String comment)
    {
        if (saddr != null)
        {
            this.saddr = new Address(saddr);
        } else
        {
            this.saddr = null;
        }

        if (daddr != null)
        {
            this.daddr = new Address(daddr);
        } else
        {
            this.daddr = null;
        }

        this.comment = comment;
    }


    public Address getSaddr()
    {
        return saddr;
    }

    public void setSaddr(Address saddr)
    {
        this.saddr = saddr;
    }

    public Address getDaddr()
    {
        return daddr;
    }

    public void setDaddr(Address daddr)
    {
        this.daddr = daddr;
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

        Rule rule = (Rule) o;

        if (daddr != null ? !daddr.equals(rule.daddr) : rule.daddr != null) return false;
        if (saddr != null ? !saddr.equals(rule.saddr) : rule.saddr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saddr != null ? saddr.hashCode() : 0;
        result = 31 * result + (daddr != null ? daddr.hashCode() : 0);
        return result;
    }
}
