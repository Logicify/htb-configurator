package com.logicify.htb.configurator.htb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Bandwidth</h1>
 * <p>Bandwidth allocated to the class. Traffic going through the class is
 * shaped to conform to specified rate. You can use Kbit, Mbit or bps,
 * Kbps and Mbps as suffices. If you don't specify any unit, bits/sec
 * are used</p>
 * <p>This class has these fields:<ul>
 * <li><b>unit</b>-it is a speed rate unit</li>
 * <li><b>prate</b>-if object uses RATE of parent HTB Class this field is true</li>
 * <li><b>pceil</b>-if object uses CEIL of parent HTB Class this field is true</li>
 * <li><b>speed</b>-this field keeps digital value of speed</li>
 * </ul></p>
 */
public class Bandwidth {
    private Unit unit;//speed rate unit
    private boolean prate;//if true uses RATE param of parent class
    private boolean pceil;//if true uses CEIL param of parent class
    private int speed; //speed of the traffic

    public Bandwidth() {
        this.prate = true;
    }

    public Bandwidth(int speed, Unit unit, boolean prate, boolean pceil) {
        if (speed < 0) this.speed = 0;
        else this.speed = speed;
        this.unit = unit;
        this.prate = prate;
        this.pceil = pceil;
    }

    public Bandwidth(String value) throws HTBException {
        if (value.equals("prate")) {
            this.prate = true;
        } else {
            if (value.equals("pceil")) {
                this.pceil = true;
            } else {
                Pattern speedPattern = Pattern.compile("(\\d+)([KkMm]((bit)|(b))?)?");
                Matcher speedMatcher = speedPattern.matcher(value);
                if (speedMatcher.find()) {
                    this.speed = Integer.parseInt(speedMatcher.group(1));
                    this.unit = Transformations.convertStringIntoUnit(speedMatcher.group(2));
                } else {
                    throw new HTBException("Bandwidth argument is wrong", new IllegalArgumentException("Bandwidth argument is wrong"), HTBException.WRONG_ARGUMENT_ERROR);
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public boolean isPrate() {
        return prate;
    }

    public void setPrate(boolean prate) {
        this.prate = prate;
    }

    public boolean isPceil() {
        return pceil;
    }

    public void setPceil(boolean pceil) {
        this.pceil = pceil;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) this.speed = 0;
        else speed = this.speed;
    }

}
