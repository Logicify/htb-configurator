package com.logicify.htb.configurator.htb.filters;

/**
 * <h1>Realm</h1>
 * <p>This class make up "route" filter rules that classify traffic
 * according to packet source/destination realms. It contains fields:<ul>
 * <li><b>srealm</b>-source realm that classify where traffic comes from</li>
 * <li><b>drealm</b>-destination realm that classify where traffic goes to</li>
 * <li><b>comment</b>-is a comment to Realm</li>
 * </ul></p>
 */
public class Realm {
    private String srealm;
    private String drealm;
    private String comment;

    public Realm(String srealm, String drealm, String comment) {
        this.srealm = srealm;
        this.drealm = drealm;
        this.comment = comment;
    }

    public String getSrealm() {
        return srealm;
    }

    public void setSrealm(String srealm) {
        this.srealm = srealm;
    }

    public String getDrealm() {
        return drealm;
    }

    public void setDrealm(String drealm) {
        this.drealm = drealm;
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
