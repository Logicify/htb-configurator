package com.logicify.htb.configurator.htb;

import org.junit.Assert;
import org.junit.Test;


/**
 * This class test methods of Transformations classes
 */
public class TestTransformations {

    @Test
    public void testFromStringToSpeedInBytes_1() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(100, Unit.KBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("100Kb");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_2() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(10, Unit.KBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("10K");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_3() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(29, Unit.KBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("29k");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_4() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(29, Unit.MBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("29m");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_5() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(45, Unit.MBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("45M");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_6() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(4, Unit.MBPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("4Mb");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromStringToSpeedInBytes_7() throws HTBException {
        SpeedInBytes sp1 = new SpeedInBytes(4, Unit.BPS);
        SpeedInBytes sp2 = Transformations.fromStringToSpeedInBytes("4");
        Assert.assertEquals(sp1.getSpeed(), sp2.getSpeed());
        Assert.assertEquals(sp1.getUnit(), sp2.getUnit());
    }

    @Test
    public void testFromSpeedInBytesToString_1() {
        SpeedInBytes sp1 = new SpeedInBytes(4, Unit.MBPS);
        String str = Transformations.fromSpeedInBytesToString(sp1);
        Assert.assertEquals("4Mb", str);
    }

    @Test
    public void testFromSpeedInBytesToString_2() {
        SpeedInBytes sp1 = new SpeedInBytes(44, Unit.KBPS);
        String str = Transformations.fromSpeedInBytesToString(sp1);
        Assert.assertEquals("44Kb", str);
    }

    @Test
    public void testFromSpeedInBytesToString_3() {
        SpeedInBytes sp1 = new SpeedInBytes(41, Unit.BPS);
        String str = Transformations.fromSpeedInBytesToString(sp1);
        Assert.assertEquals("41", str);
    }
}
