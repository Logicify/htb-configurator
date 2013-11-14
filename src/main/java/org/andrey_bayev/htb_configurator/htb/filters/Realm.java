/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb.filters;

public class Realm
{
    private String srealm;
    private String drealm;
    private String comment;

    public Realm(String srealm, String drealm, String comment)
    {
        this.srealm = srealm;
        this.drealm = drealm;
        this.comment = comment;
    }

    public String getSrealm()
    {
        return srealm;
    }

    public void setSrealm(String srealm)
    {
        this.srealm = srealm;
    }

    public String getDrealm()
    {
        return drealm;
    }

    public void setDrealm(String drealm)
    {
        this.drealm = drealm;
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

        Realm realm = (Realm) o;

        if (drealm != null ? !drealm.equals(realm.drealm) : realm.drealm != null) return false;
        if (srealm != null ? !srealm.equals(realm.srealm) : realm.srealm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = srealm != null ? srealm.hashCode() : 0;
        result = 31 * result + (drealm != null ? drealm.hashCode() : 0);
        return result;
    }
}
