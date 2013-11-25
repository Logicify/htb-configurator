package com.logicify.htb.configurator.htb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>TimeRange</h1>
 * <p>This parameter allows you to change class bandwidth during the day or
 * week. You can use multiple TIME rules. If there are several rules with
 * overlapping time periods, the last match is taken. The <b>rate</b>, <b>burst</b>,
 * <b>ceil</b> and <b>cburst</b> fields correspond to parameters RATE, BURST, CEIL
 * and CBURST.
 * <p/>
 * daysOfWeek is couple of digits in range 0-6 and represents day of week as
 * returned by date.</p>
 */
public class TimeRange {
    /**
     * Represents days of week.
     */
    public static enum DayOfWeek {
        SUNDAY("0"), MONDAY("1"), TUESDAY("2"), WEDNESDAY("3"), THURSDAY("4"), FRIDAY("5"), SATURDAY("6");

        private final String htbNumericRepresentation;

        private DayOfWeek(String htbNumericRepresentation) {
            this.htbNumericRepresentation = htbNumericRepresentation;
        }

        public String getHtbNumericRepresentation() {
            return htbNumericRepresentation;
        }

    }

    public static final String TIMERANGE_PATTERN = "((\\d+)/)?(\\d{2}:\\d{2}-\\d{2}:\\d{2});(\\d+([kKMmbpsit]+)?)(/(\\d+([kKMm]b?)?))?(,(\\d+([kKMmbpsit]+)?)(/(\\d+([kKMm]b?)?))?)?";
    private List<DayOfWeek> daysOfWeek = null;
    private boolean always;//if you don't use daysOfWeek
    private String time;
    private Bandwidth rate;
    private Bandwidth ceil;
    private SpeedInBytes burst;
    private SpeedInBytes cburst;
    private String comment;


    public TimeRange(String timeRange, String comment) throws HTBException {
        try {
            this.comment = comment;
            Pattern timeRangePattern = Pattern.compile(TIMERANGE_PATTERN);
            Matcher matcher = timeRangePattern.matcher(timeRange);
            if (!matcher.find()) {
                throw new IllegalArgumentException("wrong timeRange argument");
            }
            String weekDaysCodeString = matcher.group(2);
            if (weekDaysCodeString != null) {
                always = false;
                daysOfWeek = new ArrayList<DayOfWeek>();

                for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                    if (weekDaysCodeString.contains(dayOfWeek.getHtbNumericRepresentation())) {
                        daysOfWeek.add(dayOfWeek);
                    }
                }
            } else {
                always = true;
            }
            time = matcher.group(3);
            rate = new Bandwidth(matcher.group(4));
            burst = Transformations.fromStringToSpeedInBytes(matcher.group(7));
            ceil = new Bandwidth(matcher.group(10));
            cburst = Transformations.fromStringToSpeedInBytes(matcher.group(13));

        } catch (Exception e) {
            throw new HTBException("wrong TimeRange argument", e, HTBException.WRONG_ARGUMENT_ERROR);
        }
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return Collections.unmodifiableList(daysOfWeek);
    }

    public boolean containsDayOfWeek(DayOfWeek day) {
        return daysOfWeek.contains(day);
    }

    public void setDaysOfWeek(List<DayOfWeek> value) {
        this.daysOfWeek = value;
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

    public SpeedInBytes getBurst() {
        return burst;
    }

    public void setBurst(SpeedInBytes burst) {
        this.burst = burst;
    }

    public SpeedInBytes getCburst() {
        return cburst;
    }

    public void setCburst(SpeedInBytes cburst) {
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

    public String toString() {
        //TODO string builder
        String t = "";
        if (!always) {
            for (DayOfWeek day : getDaysOfWeek()) {
                t += day.getHtbNumericRepresentation();
            }
            t = t + '/';
        }
        t += time + ';';
        t += rate.getSpeed() + Transformations.fromUnitToString(rate.getUnit());
        if (burst.getSpeed() != 0) {
            t += '/' + Transformations.fromSpeedInBytesToString(burst);
        }
        if (ceil != null) {
            t += ',' + ceil.getSpeed() + Transformations.fromUnitToString(ceil.getUnit());
            if (cburst.getSpeed() != 0) {
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
        if (daysOfWeek != null ? !daysOfWeek.equals(timeRange.daysOfWeek) : timeRange.daysOfWeek != null) return false;
        if (rate != null ? !rate.equals(timeRange.rate) : timeRange.rate != null) return false;
        if (time != null ? !time.equals(timeRange.time) : timeRange.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = daysOfWeek != null ? daysOfWeek.hashCode() : 0;
        result = 31 * result + (always ? 1 : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (ceil != null ? ceil.hashCode() : 0);
        result = 31 * result + (burst != null ? burst.hashCode() : 0);
        result = 31 * result + (cburst != null ? cburst.hashCode() : 0);
        return result;
    }
}
