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
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;

public class TestMain
{
    final String DEFAULT_PATH="/etc/sysconfig/htb";
    String pathOfHTBDirectory="/media/wind/downloads(linux)/work task";

    @org.junit.Test
    public void testHTBFiles(){
        HTBClass htb1,htb2;
        File catalog1=new File("/media/wind/downloads(linux)/work task");
        File catalog2=new File("/media/wind/downloads(linux)/work task1");
        File htbfiles[]=catalog1.listFiles();
        InputFromFile input,input2;
        for(File file: htbfiles){
            try {
                input=new InputFromFile(file);
                htb1=input.read();
                String filename2=catalog2.getAbsolutePath()+'/'+file.getName();
                htb1.setFileName(filename2);
                OutputToFile output=new OutputToFile();
                output.write(htb1);
                input2=new InputFromFile(new File(filename2));
                htb2=input.read();
                Assert.assertEquals("Files are not equal",htb1,htb2);
            } catch (FileNotFoundException e) {
                Assert.assertTrue("File is not found", false);
            }
        }
    }




}
