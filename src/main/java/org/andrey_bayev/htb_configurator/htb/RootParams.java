/**
 * Created with IntelliJ IDEA.
 * User: andrey_bayev
 * Date: 10/26/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb;

/**
 * This class keeps params of the HTB root queuening classes
 */
public class RootParams
{
    private int defaultID;//this is ID of the default HTB class where UNCLASSIFIED traffic goes
    private int r2q; //coefficient for computing DRR (Deficit Round Robin) quanta
    private boolean dcache; //to use dequeue cache or not

    public RootParams()
    {
        defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        dcache = false;
    }

    public RootParams(int defaultId, int r2q, boolean dcache)
    {
        if (defaultId < 0) this.defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        else this.defaultID = defaultID;
        if (r2q < DefaultHTBClassValues.DEFAULT_R2Q) this.r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        else this.r2q = r2q;
        this.dcache = dcache;
    }

    public int getDefaultID()
    {
        return defaultID;
    }

    public void setDefaultID(int defaultID)
    {
        if (defaultID < 0) this.defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        else this.defaultID = defaultID;
    }

    public int getR2q()
    {
        return r2q;
    }

    public void setR2q(int r2q)
    {
        if (r2q < DefaultHTBClassValues.DEFAULT_R2Q) this.r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        else this.r2q = r2q;
    }

    public boolean isDchache()
    {
        return dcache;
    }

    public void setDchache(boolean dchache)
    {
        this.dcache = dchache;
    }

    public boolean equals(RootParams params)
    {
        if (this == null && params == null) return true;
        if (this == null && params != null) return false;
        if (this != null && params == null) return false;
        if (this.defaultID != params.defaultID) return false;
        if (this.r2q != params.r2q) return false;
        if (this.dcache != params.dcache) return false;
        return true;
    }

}
