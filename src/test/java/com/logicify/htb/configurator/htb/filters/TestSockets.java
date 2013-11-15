package com.logicify.htb.configurator.htb.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test Sockets' factory method create()
 */
public class TestSockets {
    String correctTests[];
    String incorrectTests[];
    Socket sockets[];

    @Before
    public void initTests() {
        correctTests = new String[]{"192.168.1.1", "23.45.234.12/23", "224.23.67.34:80", "32.78.23.34:23/15",
                "87.12.93.26/0xffe2:45/78", "45.123.65.23/0xadd2:27030/0x234", "45.123.65.23/0xadd2:24/1", "*:80"};
        incorrectTests = new String[]{"@(T_T)@", "323.45.35.87/23:80/45", "89.23.34.78/0xsdf:23/0xpod",
                "23.45.85.29:/23", "23.90.234.134/0xda:100/0xx"};
        sockets = new Socket[]{new Socket("192.168.1.1", 0, 0, 0), new Socket("23.45.234.12", 23, 0, 0),
                new Socket("224.23.67.34", 0, 80, 0), new Socket("32.78.23.34", 0, 23, 15),
                new Socket("87.12.93.26", 0xffe2, 45, 78), new Socket("45.123.65.23", 0xadd2, 27030, 0x234),
                new Socket("45.123.65.23", 0xadd2, 24, 1), new Socket("*", 0, 80, 0)};
    }

    @Test
    public void testConvertStringIntoSockets() {

        for (int i = 0; i < 8; i++) {
            try {
                Socket socket = Socket.create(correctTests[i]);
                Assert.assertTrue(sockets[i].equals(socket));
                System.out.println("Correct test N" + i + " is passed");
            } catch (Exception e) {
                System.out.print(e);
                Assert.assertTrue("There must be no exceptions", false);
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            try {
                Socket socket = Socket.create(incorrectTests[i]);
                Assert.assertTrue("There must be exception", false);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Incorrect test N" + i + " is passed");
            }

        }

    }

}
