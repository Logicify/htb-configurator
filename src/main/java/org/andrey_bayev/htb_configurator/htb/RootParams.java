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

    public RootParams() {
        defaultID=0;
        r2q=10;
        dcache=false;
    }

    public RootParams(int defaultId,int r2q,boolean dcache){
        if (defaultId<0) this.defaultID=0;
        else this.defaultID=defaultID;
        if (r2q<10) this.r2q=10;
        else this.r2q=r2q;
        this.dcache=dcache;
    }

    public int getDefaultID() {
        return defaultID;
    }

    public void setDefaultID(int defaultID) {
        if (defaultID<0) this.defaultID=0;
        else this.defaultID = defaultID;
    }

    public int getR2q() {
        return r2q;
    }

    public void setR2q(int r2q) {
        if (r2q<10) this.r2q=10;
        else this.r2q = r2q;
    }

    public boolean isDchache() {
        return dcache;
    }

    public void setDchache(boolean dchache) {
        this.dcache = dchache;
    }

}
