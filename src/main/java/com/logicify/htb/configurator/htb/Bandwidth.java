/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
package com.logicify.htb.configurator.htb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bandwidth allocated to the class
 */
public class Bandwidth
{
    private Unit unit;//suffices of speed rate
    private boolean prate;//if true uses RATE param of parent class
    private boolean pceil;//if true uses CEIL param of parent class
    private int speed; //speed of the traffic

    public Bandwidth()
    {
        this.prate = true;
    }

    public Bandwidth(int speed, Unit unit, boolean prate, boolean pceil)
    {
        if (speed < 0) this.speed = 0;
        else this.speed = speed;
        this.unit = unit;
        this.prate = prate;
        this.pceil = pceil;
    }

    public Bandwidth(String value) throws IllegalArgumentException
    {
        if (value.equals("prate"))
        {
            this.prate = true;
        } else
        {
            if (value.equals("pceil"))
            {
                this.pceil = true;
            } else
            {
                Pattern speedPattern = Pattern.compile("(\\d+)([KkMm]((bit)|(b))?)?");
                Matcher speedMatcher = speedPattern.matcher(value);
                if (speedMatcher.find())
                {
                    this.speed = Integer.parseInt(speedMatcher.group(1));
                    this.unit = Transformations.convertStringIntoUnit(speedMatcher.group(2));
                } else
                {
                    throw new IllegalArgumentException("Bandwidth argument is wrong");
                }


            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bandwidth bandwidth = (Bandwidth) o;

        if (pceil != bandwidth.pceil) return false;
        if (prate != bandwidth.prate) return false;
        if (speed != bandwidth.speed) return false;
        if (unit != bandwidth.unit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = unit.hashCode();
        result = 31 * result + (prate ? 1 : 0);
        result = 31 * result + (pceil ? 1 : 0);
        result = 31 * result + speed;
        return result;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public boolean isPrate()
    {
        return prate;
    }

    public void setPrate(boolean prate)
    {
        this.prate = prate;
    }

    public boolean isPceil()
    {
        return pceil;
    }

    public void setPceil(boolean pceil)
    {
        this.pceil = pceil;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        if (speed < 0) this.speed = 0;
        else speed = this.speed;
    }

}
