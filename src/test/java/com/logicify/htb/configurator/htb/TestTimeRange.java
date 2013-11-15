package com.logicify.htb.configurator.htb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests TimeRange constructor
 */
public class TestTimeRange {
    TimeRange timeRange;

    @Before
    public void InitTimeRanges() throws HTBException
    {
        timeRange=new TimeRange("60123/18:00-06:00;256Kbit/10Kb,384Kbit/12Kb",null);
    }

    @Test
    public void TestTimeRanges() throws HTBException{
        Assert.assertTrue("wrong day argument",timeRange.getDaysOfWeak()[6]);
        Assert.assertTrue("wrong day argument", timeRange.getDaysOfWeak()[0]);
        Assert.assertTrue("wrong day argument",timeRange.getDaysOfWeak()[1]);
        Assert.assertTrue("wrong day argument",timeRange.getDaysOfWeak()[2]);
        Assert.assertTrue("wrong day argument",timeRange.getDaysOfWeak()[3]);
        Assert.assertTrue("wrong time argument",timeRange.getTime().equals("18:00-06:00"));
        Assert.assertTrue("wrong rate argument",timeRange.getRate().equals(new Bandwidth(256,Unit.KBITPS,false,false)));
        Assert.assertTrue("wrong burst argument",timeRange.getBurst().equals(new SpeedInBytes(10,Unit.KBPS)));
        Assert.assertTrue("wrong ceil argument",timeRange.getCeil().equals(new Bandwidth(384,Unit.KBITPS,false,false)));
        Assert.assertTrue("wrong cburst argument",timeRange.getCburst().equals(new SpeedInBytes(12,Unit.KBPS)));

    }
}
