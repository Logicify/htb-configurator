/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/6/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb;

//this class keeps speed value(like 10Kb,100Mb,100) dividing it into digit value and Unit value
public class SpeedInBytes
{
    static final int KB = 1024;
    static final int MB = KB * 1024;

    int speed;//keeps digital value of speed
    Unit unit;

    public SpeedInBytes()
    {
    }



    public SpeedInBytes(int speed, Unit unit)
    {
        this.speed = speed;
        this.unit = unit;
    }


    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    /**
     * this method returns speed in bytes, using units KB and MB
     *
     * @return
     */
    public long getBytes()
    {
        switch (unit)
        {
            case MBPS:
                return speed * MB;
            case KBPS:
                return speed * KB;
            default:
                return speed;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpeedInBytes that = (SpeedInBytes) o;

        if (speed != that.speed) return false;
        if (unit != that.unit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = speed;
        result = 31 * result + unit.hashCode();
        return result;
    }
}
