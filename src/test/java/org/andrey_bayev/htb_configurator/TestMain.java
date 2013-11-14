/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator;

import org.andrey_bayev.htb_configurator.htb.HTBClass;
import org.andrey_bayev.htb_configurator.input.InputFromFile;
import org.andrey_bayev.htb_configurator.output.OutputToFile;
import org.apache.commons.beanutils.BeanComparator;
import org.junit.Assert;
import org.junit.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;

public class TestMain
{

    @org.junit.Test
    public void testHTBFiles()
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
                try{
                    output.write(htb1);
                }catch(Exception e){
                    System.out.println(e);
                }
                input2.setFile(file);
                htb2 = input2.read();
                //Assert.assertEquals(htb1,htb2);

        }
    }


}
