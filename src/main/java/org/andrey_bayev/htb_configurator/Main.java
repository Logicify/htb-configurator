/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator;


import org.andrey_bayev.htb_configurator.htb.HTBClass;
import org.andrey_bayev.htb_configurator.input.InputFromFile;
import org.andrey_bayev.htb_configurator.output.OutputToFile;

import java.io.FileNotFoundException;

public class Main
{
    public static void main(String args[])
    {

            InputFromFile in = new InputFromFile("/media/wind/downloads(linux)/work task/eth2-2_13.gotomeeting");
            HTBClass myhtb = in.read();
            myhtb.setFileName("/media/wind/downloads(linux)/work task/eth4-2_13.gotomeeting");
            OutputToFile out = new OutputToFile();
            out.write(myhtb);

    }
}
