/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/28/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb;


public class Transformations
{

    public static Unit convertStringIntoUnit(String s)
    {
        switch (s)
        {
            case "Kbit":
            {
                return Unit.KBIT;
            }
            case "Mbit":
            {
                return Unit.MBIT;
            }
            case "bps":
            {
                return Unit.BPS;
            }
            case "Kbps":
            {
                return Unit.KBPS;
            }
            case "Mbps":
            {
                return Unit.MBPS;
            }
            default:
                return Unit.BIT;

        }
    }

    /**
     * this static function converts string value into SpeedInBytes object that helps us to keep values like
     * 10Kb, 34Mb separating them like ((10,KB),(34,Mb)):
     *
     * @param s
     * @return
     */
    public static SpeedInBytes fromStringToSpeedInBytes(String s)
    {
        SpeedInBytes bytesSpeed = new SpeedInBytes();
        int mIndex = s.indexOf('M');
        mIndex = (mIndex != -1) ? mIndex : s.indexOf('m');
        int kIndex = s.indexOf('K');
        kIndex = (kIndex != -1) ? kIndex : s.indexOf('k');


        if (mIndex != -1)
        {
            bytesSpeed.setSpeed(Integer.parseInt(s.substring(0, mIndex)));
            bytesSpeed.setUnit(Unit.MBPS);
            return bytesSpeed;
        } else
        {
            if (kIndex != -1)
            {
                bytesSpeed.setSpeed(Integer.parseInt(s.substring(0, kIndex)));
                bytesSpeed.setUnit(Unit.KBPS);
                return bytesSpeed;
            } else
            {
                bytesSpeed.setSpeed(Integer.parseInt(s));
                bytesSpeed.setUnit(Unit.BPS);
                return bytesSpeed;
            }
        }

    }

    /**
     * converts Bytes into KBytes or MBytes
     */
    public static String fromSpeedInBytesToString(SpeedInBytes bytes)
    {

        if (bytes.getUnit() == Unit.MBPS) return (bytes.getSpeed() + "Mb");
        else if (bytes.getUnit() == Unit.KBPS) return (bytes.getSpeed() + "Kb");
        else return bytes.getSpeed() + "";
    }

    public static String fromUnitToString(Unit e)
    {
        switch (e)
        {
            case MBIT:
                return "Mbit";
            case KBIT:
                return "Kbit";
            case MBPS:
                return "Mbps";
            case KBPS:
                return "Kbps";
            case BPS:
                return "bps";
            case BIT:
                return "";
            default:
                return "";
        }
    }
}
