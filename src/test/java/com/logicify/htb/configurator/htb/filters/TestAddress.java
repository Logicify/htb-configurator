package com.logicify.htb.configurator.htb.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAddress
{
    String correctTests[];
    String incorrectTests[];
    Address addresses[];

    @Before
    public void initTests()
    {
        correctTests = new String[]{"192.168.1.1", "23.45.234.12/23", "224.23.67.34:80", "32.78.23.34:23/15",
                "87.12.93.26/0xffe2:45/78", "45.123.65.23/0xadd2:27030/0x234", "45.123.65.23/0xadd2:24/1","*:80"};
        incorrectTests = new String[]{"@(T_T)@", "323.45.35.87/23:80/45", "89.23.34.78/0xsdf:23/0xpod",
                "23.45.85.29:/23", "23.90.234.134/0xda:100/0xx"};
        addresses = new Address[]{new Address("192.168.1.1", 0, 0, 0), new Address("23.45.234.12", 23, 0, 0),
                new Address("224.23.67.34", 0, 80, 0), new Address("32.78.23.34", 0, 23, 15),
                new Address("87.12.93.26", 0xffe2, 45, 78), new Address("45.123.65.23", 0xadd2, 27030, 0x234),
                new Address("45.123.65.23", 0xadd2, 24, 1),new Address("*",0,80,0)};
    }

    @Test
    public void testConvertStringIntoAddress()
    {

        for (int i = 0; i < 8; i++)
        {
            try
            {
                Address address = Address.create(correctTests[i]);
                Assert.assertTrue(addresses[i].equals(address));
                System.out.println("Correct test N" + i + " is passed");
            } catch (Exception e)
            {
                System.out.print(e);
                Assert.assertTrue("There must be no exceptions", false);
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            try
            {
                Address address = Address.create(incorrectTests[i]);
                Assert.assertTrue("There must be exception", false);
            } catch (Exception e)
            {
                System.out.println(e);
                System.out.println("Incorrect test N" + i + " is passed");
            }

        }

    }

}
