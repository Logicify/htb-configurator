/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb.leaf;

import org.andrey_bayev.htb_configurator.htb.SpeedInBytes;
import org.andrey_bayev.htb_configurator.htb.Unit;

/**
 * this class keeps parameters for simple FIFO queueing disciplines
 */
public class FIFOParams {

    SpeedInBytes limit;//Number of packets/bytes the queue can hold

    public FIFOParams(){
        limit=new SpeedInBytes();
        this.limit.setSpeed(1000);
        this.limit.setUnit(Unit.BPS);
    }

    public FIFOParams(SpeedInBytes limit){
         if (limit.getSpeed()<0) {
             limit=new SpeedInBytes();
            this.limit.setSpeed(0);
            this.limit.setUnit(Unit.BPS);
        }
        else this.limit=limit;
    }

    public SpeedInBytes getLimit() {
        return limit;
    }

    public void setLimit(SpeedInBytes limit) {
        if ( limit.getSpeed()<0) {
            this.limit.setSpeed(0);
            this.limit.setUnit(Unit.BPS);
        }
        else this.limit=limit;
    }


}
