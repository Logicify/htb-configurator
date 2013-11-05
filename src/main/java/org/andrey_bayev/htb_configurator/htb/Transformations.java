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

    public static SpeedSuffice convertSuffice(String s) {
        switch(s)
        {
            case "Kbit":
            {
               return SpeedSuffice.KBIT;
            }
            case "Mbit":
            {
               return SpeedSuffice.MBIT;
            }
            case "bps":
            {
               return SpeedSuffice.BPS;
            }
            case "Kbps":
            {
                return SpeedSuffice.KBPS;
            }
            case "Mbps":
            {
                return SpeedSuffice.MBPS;
            }
            default: return SpeedSuffice.BIT;

        }
    }

    /**
     * this static function converts string value into long value like this:
     * a=a;
     * aKb=a*1000;
     * aMb=a*1000000;
     * @param s
     * @return
     */
    public static long fromBytesToLong(String s){
        if (s.charAt(s.length()-2)=='M' || s.charAt(s.length()-1)=='m') {  //todo: fix
            return Long.parseLong(s.substring(0,s.length()-2))*1000000;
        }
        else
        {
            if (s.charAt(s.length()-2)=='K' || s.charAt(s.length()-1)=='k') {
                return Long.parseLong(s.substring(0,s.length()-2))*1000;
            }
            else{
                return Long.parseLong(s.substring(0));
            }
        }

    }

    /**
     * converts Bytes into KBytes or MBytes
     */
    public static String fromLongToBytes(long bytes)
    {
        final int MB=1024*1024;
        final int KB=1024;
        if(bytes>=MB) return (bytes/MB+"Mb");
        else if(bytes>=KB) return (bytes/KB+"Kb");
        else return bytes+"";
    }

    public static String fromSufficeToString(SpeedSuffice e){
        switch(e)
        {
            case MBIT: return "Mbit";
            case KBIT: return "Kbit";
            case MBPS: return "Mbps";
            case KBPS: return "Kbps";
            case BPS: return "bps";
            case BIT: return "";
            default: return "";
        }
    }
}
