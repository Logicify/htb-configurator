/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb.leaf;

/**
 * this class keeps parameters for simple FIFO queueing disciplines
 */
public class FIFOParams {

    boolean packets;//to use packets or not
    long limit;//Number of packets/bytes the queue can hold

    public FIFOParams(){
        this.packets=true;
        this.limit=1000;
    }

    public FIFOParams(boolean packets,long limit){
        this.packets=packets;
        if (limit<0) this.limit=0;
        else this.limit=limit;
    }

    public boolean isPackets() {
        return packets;
    }

    public void setPackets(boolean packets) {
        this.packets = packets;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        if ( limit<0) this.limit = 0;
        else this.limit=limit;
    }


}
