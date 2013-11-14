/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/28/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transformations
{

    public static Unit convertStringIntoUnit(String s)
    {
        switch (s)
        {
            case "Kbit":
            {
                return Unit.KBITPS;
            }
            case "Mbit":
            {
                return Unit.MBITPS;
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
                return Unit.BITPS;

        }
    }

    /**
     * this static function converts string value into SpeedInBytes object that helps us to keep values like
     * 10Kb, 34Mb separating them like ((10,KB),(34,Mb)):
     *
     * @param s
     * @return
     */
    public static SpeedInBytes fromStringToSpeedInBytes(String s) throws IllegalArgumentException
    {
        final String BYTE_SPEED_PATTERN = "(\\d+)([MmKk]b?)?";
        final String MBYTE_PATTERN = "[Mm]b?";
        final String KBYTE_PATTERN = "[Kk]b?";
        Pattern speedPattern = Pattern.compile(BYTE_SPEED_PATTERN);
        Matcher speedMatcher = speedPattern.matcher(s);
        if (!speedMatcher.find())
        {
            throw new IllegalArgumentException("wrong string that represents speed in bytes");
        }
        SpeedInBytes bytesSpeed = new SpeedInBytes();
        bytesSpeed.setSpeed(Integer.parseInt(speedMatcher.group(1)));
        String unit = speedMatcher.group(2);
        if (unit.matches(MBYTE_PATTERN))
        {
            bytesSpeed.setUnit(Unit.MBPS);
        } else
        {
            if (unit.matches(KBYTE_PATTERN))
            {
                bytesSpeed.setUnit(Unit.KBPS);
            } else
            {
                bytesSpeed.setUnit(Unit.BPS);
            }
        }
        return bytesSpeed;

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
            case MBITPS:
                return "Mbit";
            case KBITPS:
                return "Kbit";
            case MBPS:
                return "Mbps";
            case KBPS:
                return "Kbps";
            case BPS:
                return "bps";
            case BITPS:
                return "";
            default:
                return "";
        }
    }
}
