/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.htb;

/**
 * This class allows you to change class bandwidth during the day or
 #	week
 */
public class TimeRange {

    private boolean daysOfWeak[];
    private boolean always;//if you don't use daysOfWeak
    private String time;
    private Bandwidth rate;
    private Bandwidth ceil;
    private long burst;
    private long cburst;
    private String comment;

    public TimeRange(String timeRange,String comment){
        this.comment=comment;
        daysOfWeak=new boolean[7];
        String parts[]=timeRange.split(";");
        String timePart=parts[0];
        String bandWidthPart=parts[1];
        if (timePart.charAt(2)==':')
        {
            always=true;
            time=timePart;
        }
        else{
            parts=timePart.split("/");
            int days=Integer.parseInt(parts[0]);
            while(days>0)
            {
                daysOfWeak[days%10]=true;
                days/=10;
            }
            time=parts[1];
        }
        parts=bandWidthPart.split(",");
        String rateParts=parts[0];
        String ceilParts=(parts.length==2) ? parts[1] : "";
        String rat[]=rateParts.split("/");
        rate=new Bandwidth(rat[0]);
        if(rat.length==2){
            burst=Transformations.fromBytesToLong(rat[1]);
        } else {
            burst=0;
        }
        if (ceilParts!="")
        {
            String ceilpts[]=ceilParts.split("/");
            ceil=new Bandwidth(ceilpts[0]);
            if (ceilpts.length==2){
                cburst=Transformations.fromBytesToLong(ceilpts[1]);
            }
            else{
                cburst=0;
            }
        }
        else
        {
            ceil=null;
            cburst=0;
        }
    }

    public boolean[] getDaysOfWeak() {
        return daysOfWeak;
    }

    public void setDaysOfWeak(boolean[] daysOfWeak) {
        this.daysOfWeak = daysOfWeak;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bandwidth getRate() {
        return rate;
    }

    public void setRate(Bandwidth rate) {
        this.rate = rate;
    }

    public Bandwidth getCeil() {
        return ceil;
    }

    public void setCeil(Bandwidth ceil) {
        this.ceil = ceil;
    }

    public long getBurst() {
        return burst;
    }

    public void setBurst(long burst) {
        this.burst = burst;
    }

    public long getCburst() {
        return cburst;
    }

    public void setCburst(long cburst) {
        this.cburst = cburst;
    }


    public boolean isAlways() {
        return always;
    }

    public void setAlways(boolean always) {
        this.always = always;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){
        String t="";
        if(!always)
        {
            for(int i=0;i<=6;i++){
                if (daysOfWeak[i]) t+=i;
            }
            t=t+'/';
        }
        t+=time+';';
        t+=rate.getSpeed()+Transformations.fromSufficeToString(rate.getSufficeOfSpeed());
        if(burst!=0){
           t+='/'+Transformations.fromLongToBytes(burst);
        }
        if(ceil!=null){
            t+=','+ceil.getSpeed()+Transformations.fromSufficeToString(ceil.getSufficeOfSpeed());
            if(cburst!=0){
                t+='/'+Transformations.fromLongToBytes(cburst);
            }
        }
        return t;
    }



}
