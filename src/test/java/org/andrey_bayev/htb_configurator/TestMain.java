/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator;

import junit.framework.Assert;
import org.andrey_bayev.htb_configurator.htb.HTBClass;
import org.andrey_bayev.htb_configurator.input.InputFromFile;
import org.andrey_bayev.htb_configurator.output.OutputToFile;
//import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.File;

public class TestMain
{

    @org.junit.Test
    public void testHTBFiles() throws Exception
    {
        HTBClass htb1, htb2;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        File catalog1 = new File(loader.getResource("ExpectedFiles").getPath());
        File catalog2 = new File(loader.getResource("ActualFiles").getPath());
        File htbfiles[] = catalog1.listFiles();

        InputFromFile input = new InputFromFile(), input2 = new InputFromFile();

        for (File file : htbfiles)
        {
            input.setFile(file);
            htb1 = input.read();
            String filename2 = catalog2.getAbsolutePath() + '/' + file.getName();
            htb1.setFileName(filename2);
            OutputToFile output = new OutputToFile();
            output.write(htb1);
            input2.setFile(file);
            htb2 = input2.read();
            //todo: make all test work
            //Assert.assertTrue(EqualsBuilder.reflectionEquals(htb1, htb2, new String[]{"fileName"}));
        }
    }


}
