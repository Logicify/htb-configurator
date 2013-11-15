package com.logicify.htb.configurator.htb.leaf;

import com.logicify.htb.configurator.htb.DefaultHTBClassValues;
import com.logicify.htb.configurator.htb.SpeedInBytes;

/**
 * <h1>SFQParams</h1>
 * <p>This class keeps sfq parameters if user set LEAF=sfq. It has fields:<ul>
 * <li><b>quantum</b>-it keeps number of bytes a stream is allowed to dequeue before next queue gets a turn</li>
 * <li><b>perturb</b>-it keeps period of hash function perturbation</li>
 * </ul></p>
 */
public class SFQParams {
    private SpeedInBytes quantum;//Amount of data in bytes a stream is allowed to dequeue before next queue gets a turn.
    private int perturb;//Period of hash function perturbation.

    public SFQParams() {
        this.quantum = new SpeedInBytes();
        this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);//default MTU packet value
        this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
    }

    public SFQParams(SpeedInBytes quantum, int perturb) {

        if (quantum.getSpeed() < 0) {
            this.quantum = new SpeedInBytes();
            this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);
            this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.quantum = quantum;
        if (perturb < 0) {
            this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
        } else {
            this.perturb = perturb;
        }
    }

    public SpeedInBytes getQuantum() {
        return quantum;
    }

    public void setQuantum(SpeedInBytes quantum) {
        if (quantum.getSpeed() < 0) {
            this.quantum.setSpeed(DefaultHTBClassValues.DEFAULT_QUANTUM_SPEED);
            this.quantum.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.quantum = quantum;
    }

    public int getPerturb() {
        return perturb;
    }

    public void setPerturb(int perturb) {
        if (perturb < 0) this.perturb = DefaultHTBClassValues.DEFAULT_PERTURB;
        else this.perturb = perturb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SFQParams sfqParams = (SFQParams) o;

        if (perturb != sfqParams.perturb) return false;
        if (quantum != null ? !quantum.equals(sfqParams.quantum) : sfqParams.quantum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantum != null ? quantum.hashCode() : 0;
        result = 31 * result + perturb;
        return result;
    }
}
