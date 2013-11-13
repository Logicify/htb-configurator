package org.andrey_bayev.htb_configurator.htb.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/12/13
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAddress
{

    //TODO You can use static array initializer in Java, which is basically a generating expression:
    // like int[] something = {1,2,3} This automatically takes care of the size and stuff, and is easier to read.
    // please redo examples.

    String correctTests[] = {};
    String incorrectTests[];
    Address addresses[];

    @Before
    public void initTests()
    {
        correctTests = new String[7];
        incorrectTests = new String[5];
        addresses = new Address[7];
        correctTests[0] = "192.168.1.1";
        correctTests[1] = "23.45.234.12/23";
        correctTests[2] = "224.23.67.34:80";
        correctTests[3] = "32.78.23.34:23/15";
        correctTests[4] = "87.12.93.26/0xffe2:45/78";
        correctTests[5] = "45.123.65.23/0xadd2:27030/0x234";
        correctTests[6] = "45.123.65.23/0xadd2:24/1";
        addresses[0] = new Address("192.168.1.1", 0, 0, 0);
        addresses[1] = new Address("23.45.234.12", 23, 0, 0);
        addresses[2] = new Address("224.23.67.34", 0, 80, 0);
        addresses[3] = new Address("32.78.23.34", 0, 23, 15);
        addresses[4] = new Address("87.12.93.26", 0xffe2, 45, 78);
        addresses[5] = new Address("45.123.65.23", 0xadd2, 27030, 0x234);
        addresses[6] = new Address("45.123.65.23", 0xadd2, 24, 1);
        incorrectTests[0] = "@(T_T)@";
        incorrectTests[1] = "323.45.35.87/23:80/45";
        incorrectTests[2] = "89.23.34.78/0xsdf:23/0xpod";
        incorrectTests[3] = "23.45.85.29:/23";
        incorrectTests[4] = "23.90.234.134/0xda:100/0xx";
    }

    @Test
    public void testConvertStringIntoAddress()
    {

        for (int i = 0; i < 7; i++)
        {
            try
            {
                Address address = Address.convertStringIntoAddress(correctTests[i]);
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
                Address address = Address.convertStringIntoAddress(incorrectTests[i]);
                Assert.assertTrue("There must be exception", false);
            } catch (Exception e)
            {
                System.out.println(e);
                System.out.println("Incorrect test N" + i + " is passed");
            }

        }

    }

}
