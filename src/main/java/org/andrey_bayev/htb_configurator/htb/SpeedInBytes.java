/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/6/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb;

//this class keeps speed value(like 10Kb,100Mb,100) dividing it into digit value and SpeedSuffice value
public class SpeedInBytes {

    int speed;//keeps digital value of speed
    SpeedSuffice suf;

    public SpeedInBytes(){

    };

    public SpeedInBytes(int speed, SpeedSuffice suf) {
        this.speed = speed;
        this.suf = suf;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public SpeedSuffice getSuf() {
        return suf;
    }

    public void setSuf(SpeedSuffice suf) {
        this.suf = suf;
    }

}
