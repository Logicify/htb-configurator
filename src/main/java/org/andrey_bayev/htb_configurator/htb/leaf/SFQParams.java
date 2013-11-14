/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.leaf;

import org.andrey_bayev.htb_configurator.htb.DefaultHTBClassValues;
import org.andrey_bayev.htb_configurator.htb.SpeedInBytes;

/**
 * This class keeps sfq parameters if user set LEAF=sfq;
 */
public class SFQParams
{
    private SpeedInBytes quantum;//Amount of data in bytes a stream is allowed to dequeue before next queue gets a turn.
    private int perturb;//Period of hash function perturbation.
    // why not name it hashPerturbationPeriod?

    public SFQParams()
    {
        this.quantum = new SpeedInBytes();
        this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);//default MTU packet value
        this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
    }

    public SFQParams(SpeedInBytes quantum, int perturb)
    {

        if (quantum.getSpeed() < 0)
        {
            this.quantum = new SpeedInBytes();
            this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);
            this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.quantum = quantum;
        if (perturb < 0) this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
            // constants class of SpeedDefaults. There should be no 'magic numbers' in code.
        else this.perturb = perturb;
    }

    public SpeedInBytes getQuantum()
    {
        return quantum;
    }

    public void setQuantum(SpeedInBytes quantum)
    {
        if (quantum.getSpeed() < 0)
        {
            this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);
            this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.quantum = quantum;
    }

    public int getPerturb()
    {
        return perturb;
    }

    public void setPerturb(int perturb)
    {
        if (perturb < 0) this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
        else this.perturb = perturb;
    }
}
