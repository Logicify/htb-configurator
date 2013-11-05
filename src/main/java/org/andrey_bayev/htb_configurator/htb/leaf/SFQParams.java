/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.leaf;

/**
 * This class keeps sfq parameters if user set LEAF=sfq;
 */
public class SFQParams{
    private long quantum;//Amount of data in bytes a stream is allowed to dequeue before next queue gets a turn.
    private int perturb;//Period of hash function perturbation.

    public SFQParams()
    {
        this.quantum=1600;//default MTU packet value
        this.perturb=10;
    }

    public SFQParams(long quantum, int perturb)
    {
        if(quantum<0) this.quantum=1600;
        else this.quantum=quantum;
        if(perturb<0) this.perturb=10;
        else this.perturb = perturb;
    }

    public long getQuantum() {
        return quantum;
    }

    public void setQuantum(long quantum) {
        if(quantum<0) this.quantum=1600;
        else this.quantum=quantum;
    }

    public int getPerturb() {
        return perturb;
    }

    public void setPerturb(int perturb) {
        if(perturb<0) this.perturb=10;
        else this.perturb = perturb;
    }
}
