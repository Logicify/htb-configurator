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
    static final int MB=1048576;
    static final int KB=1024;

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
     * aKb=a*1024;
     * aMb=a*1000000;
     * @param s
     * @return
     */
    public static SpeedInBytes fromStringToSpeedInBytes(String s){
        SpeedInBytes bytesSpeed=new SpeedInBytes();
        int mIndex=s.indexOf('M');
        mIndex=(mIndex!=-1)?mIndex:s.indexOf('m');
        int kIndex=s.indexOf('K');
        kIndex=(kIndex!=-1)?kIndex:s.indexOf('k');


        if (mIndex!=-1) {  //todo: fix
            bytesSpeed.setSpeed(Integer.parseInt(s.substring(0,mIndex)));
            bytesSpeed.setSuf(SpeedSuffice.MBPS);
            return bytesSpeed;
        }
        else
        {
            if (kIndex!=-1) {
                bytesSpeed.setSpeed(Integer.parseInt(s.substring(0,kIndex)));
                bytesSpeed.setSuf(SpeedSuffice.KBPS);
                return bytesSpeed;
            }
            else{
                bytesSpeed.setSpeed(Integer.parseInt(s));
                bytesSpeed.setSuf(SpeedSuffice.BPS);
                return bytesSpeed;
            }
        }

    }

    /**
     * converts Bytes into KBytes or MBytes
     */
    public static String fromSpeedInBytesToString(SpeedInBytes bytes)
    {

        if(bytes.getSuf()==SpeedSuffice.MBPS) return (bytes.getSpeed()+"Mb");
        else if(bytes.getSuf()==SpeedSuffice.KBPS) return (bytes.getSpeed()+"Kb");
        else return bytes.getSpeed()+"";
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
