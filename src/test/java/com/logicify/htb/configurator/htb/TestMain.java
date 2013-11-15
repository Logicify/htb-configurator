package com.logicify.htb.configurator.htb;

import com.logicify.htb.configurator.htb.HTBClass;
import com.logicify.htb.configurator.input.InputFromFile;
import com.logicify.htb.configurator.output.OutputToFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;

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
            Assert.assertTrue(EqualsBuilder.reflectionEquals(htb1, htb2, new String[]{"fileName","comments"}));
        }
    }


}
