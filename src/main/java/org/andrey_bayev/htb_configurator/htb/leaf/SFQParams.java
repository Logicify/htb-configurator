/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.leaf;

import org.andrey_bayev.htb_configurator.htb.SpeedInBytes;
import org.andrey_bayev.htb_configurator.htb.SpeedSuffice;

/**
 * This class keeps sfq parameters if user set LEAF=sfq;
 */
public class SFQParams{
    private SpeedInBytes quantum;//Amount of data in bytes a stream is allowed to dequeue before next queue gets a turn.
    private int perturb;//Period of hash function perturbation.

    public SFQParams()
    {
        this.quantum=new SpeedInBytes();
        this.quantum.setSpeed(1600);//default MTU packet value
        this.quantum.setSuf(SpeedSuffice.BPS);
        this.perturb=10;
    }

    public SFQParams(SpeedInBytes quantum, int perturb)
    {

        if(quantum.getSpeed()<0) {
            this.quantum=new SpeedInBytes();
            this.quantum.setSpeed(1600);
            this.quantum.setSuf(SpeedSuffice.BPS);
        }
        else this.quantum=quantum;
        if(perturb<0) this.perturb=10;
        else this.perturb = perturb;
    }

    public SpeedInBytes getQuantum(){
        return quantum;
    }

    public void setQuantum(SpeedInBytes quantum) {
        if(quantum.getSpeed()<0) {
            this.quantum.setSpeed(1600);
            this.quantum.setSuf(SpeedSuffice.BPS);
        }
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
