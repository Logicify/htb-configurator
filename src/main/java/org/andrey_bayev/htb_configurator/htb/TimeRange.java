/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb;

import java.util.Arrays;

/**
 * This class allows you to change class bandwidth during the day or
 * week
 */
public class TimeRange
{
    private boolean daysOfWeak[];
    private boolean always;//if you don't use daysOfWeak
    private String time;
    private Bandwidth rate;
    private Bandwidth ceil;
    private SpeedInBytes burst;
    private SpeedInBytes cburst;
    private String comment;

    public TimeRange(String timeRange, String comment)
    {
        // todo: regex again!
        this.comment = comment;
        daysOfWeak = new boolean[7];
        String parts[] = timeRange.split(";");
        String timePart = parts[0];
        String bandWidthPart = parts[1];
        if (timePart.charAt(2) == ':')
        {
            always = true;
            time = timePart;
        } else
        {
            parts = timePart.split("/");
            int days = Integer.parseInt(parts[0]);
            while (days > 0)
            {
                daysOfWeak[days % 10] = true;
                days /= 10;
            }
            time = parts[1];
        }
        parts = bandWidthPart.split(",");
        String partOfTimeWithRate = parts[0];
        String partOfTimeWithCeil = (parts.length == 2) ? parts[1] : "";
        String rat[] = partOfTimeWithRate.split("/");
        rate = new Bandwidth(rat[0]);
        if (rat.length == 2)
        {
            burst = Transformations.fromStringToSpeedInBytes(rat[1]);
        } else
        {
            burst.setSpeed(0);
            burst.setUnit(Unit.BPS);
        }
        if (partOfTimeWithCeil != "")
        {
            String partsOfCeil[] = partOfTimeWithCeil.split("/");
            ceil = new Bandwidth(partsOfCeil[0]);
            if (partsOfCeil.length == 2)
            {
                cburst = Transformations.fromStringToSpeedInBytes(partsOfCeil[1]);
            } else
            {
                cburst.setSpeed(0);
                cburst.setUnit(Unit.BPS);
            }
        } else
        {
            ceil = null;
            cburst.setSpeed(0);
            cburst.setUnit(Unit.BPS);
        }
    }

    public boolean[] getDaysOfWeak()
    {
        return daysOfWeak;
    }

    public void setDaysOfWeak(boolean[] daysOfWeak)
    {
        this.daysOfWeak = daysOfWeak;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public Bandwidth getRate()
    {
        return rate;
    }

    public void setRate(Bandwidth rate)
    {
        this.rate = rate;
    }

    public Bandwidth getCeil()
    {
        return ceil;
    }

    public void setCeil(Bandwidth ceil)
    {
        this.ceil = ceil;
    }

    public SpeedInBytes getBurst()
    {
        return burst;
    }

    public void setBurst(SpeedInBytes burst)
    {
        this.burst = burst;
    }

    public SpeedInBytes getCburst()
    {
        return cburst;
    }

    public void setCburst(SpeedInBytes cburst)
    {
        this.cburst = cburst;
    }


    public boolean isAlways()
    {
        return always;
    }

    public void setAlways(boolean always)
    {
        this.always = always;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String toString()
    {
        String t = "";
        if (!always)
        {
            for (int i = 0; i <= 6; i++)
            {
                if (daysOfWeak[i]) t += i;
            }
            t = t + '/';
        }
        t += time + ';';
        t += rate.getSpeed() + Transformations.fromUnitToString(rate.getUnit());
        if (burst.getSpeed() != 0)
        {
            t += '/' + Transformations.fromSpeedInBytesToString(burst);
        }
        if (ceil != null)
        {
            t += ',' + ceil.getSpeed() + Transformations.fromUnitToString(ceil.getUnit());
            if (cburst.getSpeed() != 0)
            {
                t += '/' + Transformations.fromSpeedInBytesToString(cburst);
            }
        }
        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeRange timeRange = (TimeRange) o;

        if (always != timeRange.always) return false;
        if (burst != null ? !burst.equals(timeRange.burst) : timeRange.burst != null) return false;
        if (cburst != null ? !cburst.equals(timeRange.cburst) : timeRange.cburst != null) return false;
        if (ceil != null ? !ceil.equals(timeRange.ceil) : timeRange.ceil != null) return false;
        if (!Arrays.equals(daysOfWeak, timeRange.daysOfWeak)) return false;
        if (rate != null ? !rate.equals(timeRange.rate) : timeRange.rate != null) return false;
        if (time != null ? !time.equals(timeRange.time) : timeRange.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = daysOfWeak != null ? Arrays.hashCode(daysOfWeak) : 0;
        result = 31 * result + (always ? 1 : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (ceil != null ? ceil.hashCode() : 0);
        result = 31 * result + (burst != null ? burst.hashCode() : 0);
        result = 31 * result + (cburst != null ? cburst.hashCode() : 0);
        return result;
    }
}
